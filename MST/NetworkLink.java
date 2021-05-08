import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class NetworkLink {
	static class Node implements Comparable<Node>{
		int start;
		int end;
		int weight;
		
		Node(int a, int b, int c) {
			start = a;
			end = b;
			weight = c;
		}

		@Override
		public int compareTo(Node target) {
			return weight - target.weight;
		}
	}
	
	static int parent[];
	public static void main(String[] args) throws IOException {
		// 유니온 파인드
		int sum = 0;
		int start, end;
		Node n;
		PriorityQueue<Node> queue = new PriorityQueue<>();
		int N, M;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N + 1];
		for(int i = 1; i <= N; ++i)
			parent[i] = i;
		
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			
			queue.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		while(!queue.isEmpty()) {
			n = queue.poll();
			start = find(n.start);
			end = find(n.end);
			if(start == end)
				continue;
			union(start, end);
			sum += n.weight;
		}
		
		System.out.println(sum);
	}
	
	static int find(int node) {
		if(node == parent[node])
			return node;
		parent[node] = find(parent[node]);
		return parent[node];
	}
	
	static void union(int start, int end) {
		parent[end] = start;
	}
}

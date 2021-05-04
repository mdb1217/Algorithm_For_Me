import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HardToLove {
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
		// MST
		boolean success = true;
		int sum = 0;
		Node n;
		int start, end, weight;
		PriorityQueue<Node> queue = new PriorityQueue<>();
		char c[];
		int N, M;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		c = new char[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; ++i) {
			c[i] = st.nextToken().charAt(0);
			parent[i] = i;
		}
		
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			
			queue.add(new Node(start, end, weight));
		}
		
		while(!queue.isEmpty()) {
			n = queue.poll();
			if(c[n.start] == c[n.end])
				continue;
			start = find(n.start);
			end = find(n.end);
			if(start == end)
				continue;
			union(start, end);
			sum += n.weight;
		}
		
		start = find(1);
		for(int i = 2; i <= N; ++i) {
			if(start != find(i)) {
				success = false;
				break;
			}
		}
		
		System.out.println(success? sum : -1);
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CityDividePlan {
	static class Node implements Comparable<Node> {
		int start;
		int end;
		int cost;
		
		Node(int a, int b, int c) {
			start = a;
			end = b;
			cost = c;
		}

		@Override
		public int compareTo(Node target) {
			return cost - target.cost;
		}
	}
	
	static int parent[];
	public static void main(String[] args) throws IOException {
		// MST
		Node n;
		int N, M;
		int start, end, cost;
		PriorityQueue<Node> queue = new PriorityQueue<>();
		int sum = 0;
		int max = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		for(int i = 1; i <= N; ++i)
			parent[i]= i;
		
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			queue.add(new Node(start, end, cost));
		}
		
		while(!queue.isEmpty()) {
			n = queue.poll();
			start = find(n.start);
			end = find(n.end);
			if(start == end)
				continue;
			union(start, end);
			sum += n.cost;
			max = Math.max(max, n.cost);
		}
		
		System.out.println(sum - max);
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

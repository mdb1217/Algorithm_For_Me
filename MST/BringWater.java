import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BringWater {
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
	
	static int money[];
	static int sum[];
	static int parent[];
	static boolean Visited[];
	public static void main(String[] args) throws IOException {
		// MST
		PriorityQueue<Node> queue = new PriorityQueue<>();
		Node n;
		int N;
		int ans = 0;
		int weight, start, end;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		money = new int[N + 1];
		parent = new int[N + 1];
		sum = new int[N + 1];
		Visited = new boolean[N + 1];
		
		for(int i = 1; i <= N; ++i) {
			 money[i] = Integer.parseInt(br.readLine());
			 parent[i] = i;
		}
		
		for(int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; ++j) {
				 weight = Integer.parseInt(st.nextToken());
				 if(i < j)
					 queue.add(new Node(i, j, weight));
			}
		}
		
		while(!queue.isEmpty()) {
			n = queue.poll();
			start = find(n.start);
			end = find(n.end);
			if(start == end)
				continue;
			if(n.weight < money[start] || n.weight < money[end]) {
				if(money[start] < money[end]) {
					union(start, end);
					sum[start] += sum[end] + n.weight;
				}
				else {
					union(end, start);
					sum[end] += sum[start] + n.weight;
				}
			}
		}
		
		for(int i = 1; i <= N; ++i) {
			start = find(i);
			if(!Visited[start]) {
				ans += sum[start] + money[start];
				Visited[start] = true;
			}
		}
		
		System.out.println(ans);
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

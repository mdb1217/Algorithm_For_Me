import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ElectricityShortage {
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
	
	static boolean power_plant[];
	static int parent[];
	public static void main(String[] args) throws IOException {
		// MST
		PriorityQueue<Node> queue = new PriorityQueue<>();
		int N, M, K;
		int start, end, weight, sum = 0;
		Node n;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		power_plant = new boolean[N + 1];
		for(int i = 1; i <= N; ++i)
			parent[i] = i;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; ++i)
			power_plant[Integer.parseInt(st.nextToken())] = true;
		
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			
			queue.add(new Node(start, end, weight));
		}
		
		while(!queue.isEmpty()) {
			n = queue.poll();
			start = find(n.start);
			end = find(n.end);
			if(start == end)
				continue;
			
			if(power_plant[start] && power_plant[end])
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
		if(power_plant[end])
			parent[start] = end;
		else
			parent[end] = start;
	}
}

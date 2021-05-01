import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class TravelSchool {
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
			if(weight > target.weight)
				return 1;
			else if(weight == target.weight)
				return 0;
			else
				return -1;
		}
	}
	
	static int parent[];
	static int parent2[];
	public static void main(String[] args) throws IOException {
		// MST
		int cnt = 0;
		int cnt2 = 0;
		Node n;
		int start, end, road;
		int N, M;
		PriorityQueue<Node> queue = new PriorityQueue<>();
		PriorityQueue<Node> queue2 = new PriorityQueue<>(Collections.reverseOrder());
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		parent2 = new int[N + 1];
		
		for(int i = 1; i <= N; ++i) {
			parent[i] = i;
			parent2[i] = i; 
		}
		
		for(int i = 0; i < M + 1; ++i) {
			st = new StringTokenizer(br.readLine());
			
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			road = Integer.parseInt(st.nextToken());
			
			n = new Node(start, end, road);
			queue.add(n);
			queue2.add(n);
		}
		
		while(!queue.isEmpty()) {
			n = queue.poll();
			start = find(n.start);
			end = find(n.end);
			if(start == end)
				continue;
			union(start, end);
			if(n.weight == 0)
				++cnt;
		}
		
		while(!queue2.isEmpty()) {
			n = queue2.poll();
			start = find2(n.start);
			end = find2(n.end);
			if(start == end)
				continue;
			union2(start, end);
			if(n.weight == 0)
				++cnt2;
		}
		
		System.out.println((cnt * cnt) - (cnt2 * cnt2));
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
	
	static int find2(int node) {
		if(node == parent2[node])
			return node;
		parent2[node] = find2(parent2[node]);
		return parent2[node];
	}
	
	static void union2(int start, int end) {
		parent2[end] = start;
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PowerShortage {
	static class Node implements Comparable<Node> {
		int start;
		int end;
		int distance;
		
		Node(int a, int b, int c) {
			start = a;
			end = b;
			distance = c;
		}
		
		@Override
		public int compareTo(Node target) {
			return distance - target.distance;
		}
	}

	static int parent[];
	public static void main(String[] args) throws IOException {
		// MST
		StringBuilder sb = new StringBuilder();
		Node node;
		PriorityQueue<Node> queue = new PriorityQueue<>();
		int m, n = 0;
		int x, y, z;
		int sum;
		int min_sum;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			sum = 0;
			min_sum = 0;
			
			if(m == 0 && n == 0)
				break;
			
			parent = new int[m];
			for(int i = 0; i < m; ++i)
				parent[i] = i;
			
			for(int i = 0; i < n; ++i) {
				st = new StringTokenizer(br.readLine());
				
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				z = Integer.parseInt(st.nextToken());
				
				sum += z;
				queue.add(new Node(x, y, z));
			}
			
			while(!queue.isEmpty()) {
				node = queue.poll();
				x = find(node.start);
				y = find(node.end);
				if(x == y)
					continue;
				union(x, y);
				min_sum += node.distance;
			}
			
			sb.append(sum - min_sum).append("\n");
		}
		
		System.out.print(sb);
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

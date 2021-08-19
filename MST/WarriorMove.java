import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class WarriorMove {
	static class Node implements Comparable<Node>{
		int start;
		int end;
		int width;
		
		Node(int a, int b, int c) {
			start = a;
			end = b;
			width = c;
		}

		@Override
		public int compareTo(Node target) {
			return target.width - width;
		}
	}

	static int parent[];
	public static void main(String[] args) throws IOException {
		// MST
		Node n;
		PriorityQueue<Node> queue = new PriorityQueue<>();
		int A, B, C;
		int start, end;
		int max = 0;
		int P, W;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		P = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		parent = new int[P + 1];
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= P; i++)
			parent[i] = i;
		
		for(int i = 0; i < W; ++i) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			queue.add(new Node(A, B, C));
		}
		
		while(!queue.isEmpty()) {
			n = queue.poll();
			A = find(n.start);
			B = find(n.end);
			if(A == B)
				continue;
			union(A, B);
			
			if(find(start) == find(end)) {
				max = n.width;
				break;
			}
		}
		
		System.out.println(max);
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MakeConstellation {
	static class Graph {
		double x;
		double y;
		
		Graph(double a, double b) {
			x = a;
			y = b;
		}
	}
	
	static class Node implements Comparable<Node>{
		int start;
		int end;
		double distance;
		
		Node(int a, int b, double c) {
			start = a;
			end = b;
			distance = c;
		}
		
		@Override
		public int compareTo(Node target) {
			if(distance - target.distance > 0)
				return 1;
			else
				return -1;
		}
		
	}

	static int parent[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		// MST
		Node n;
		ArrayList<Graph> list = new ArrayList<>();
		PriorityQueue<Node> queue = new PriorityQueue<>();
		double x, y;
		double ans = 0;
		int start, end;
		int N;
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		parent = new int[N];
		
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			x = Double.parseDouble(st.nextToken());
			y = Double.parseDouble(st.nextToken());
			
			for(int j = 0; j < list.size(); ++j)
				queue.add(new Node(j, i, Math.sqrt(Math.pow(x - list.get(j).x, 2) + Math.pow(y - list.get(j).y, 2))));
			
			list.add(new Graph(x, y));
			
			parent[i] = i;
		}
		
		while(!queue.isEmpty()) {
			n = queue.poll();
			start = find(n.start);
			end = find(n.end);
			if(start == end)
				continue;
			
			union(start, end);
			ans += n.distance;
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

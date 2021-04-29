import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BeYourBridge {
	static class Node {
		int start;
		int end;
		
		Node(int a, int b) {
			start = a;
			end = b;
		}
	}
	
	static int parent[];
	public static void main(String[] args) throws IOException {
		// Union Find
		Queue<Node> queue = new LinkedList<>();
		int a, b;
		int N;
		Node n;
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		parent = new int[N + 1];
		for(int i = 1; i <= N; ++i)
			parent[i] = i;
		
		for(int i = 0; i < N - 2; ++i) {
			st = new StringTokenizer(br.readLine());
			
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			queue.add(new Node(a, b));
		}
		
		while(!queue.isEmpty()) {
			n = queue.poll();
			a = find(n.start);
			b = find(n.end);
			if(a == b)
				continue;
			union(a, b);
		}
		
		a = find(1);
		for(int i = 2; i <= N; ++i) {
			if(a != find(i)) {
				System.out.println(1 + " " + i);
				break;
			}
		}
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

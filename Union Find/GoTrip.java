import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class GoTrip {
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
		// 유니온 파인드
		boolean yes = true;
		ArrayList<Integer> list = new ArrayList<>();
		Queue<Node> queue = new LinkedList<>();
		int N, M;
		int start, end;
		Node n;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		parent = new int[N + 1];
		for(int i = 1; i <= N; ++i) {
			parent[i] = i;
			
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; ++j) {
				if(Integer.parseInt(st.nextToken()) == 1)
					queue.add(new Node(i, j));
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; ++i)
			list.add(Integer.parseInt(st.nextToken()));
		
		while(!queue.isEmpty()) {
			n = queue.poll();
			start = find(n.start);
			end = find(n.end);
			if(start == end)
				continue;
			union(start, end);
		}
		
		for(int i = 0; i < list.size() - 1; ++i) {
			if(find(list.get(i)) != find(list.get(i + 1))) {
				yes = false;
				break;
			}
		}
		
		System.out.println(yes? "YES" : "NO");
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

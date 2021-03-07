import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SogangGround {
	static class Graph implements Comparable<Graph>{
		int city;
		int distance;
		
		Graph(int a, int b) {
			city = a;
			distance = b;
		}

		@Override
		public int compareTo(Graph target) {
			return this.distance - target.distance;
		}
	}

	static int n, m, r;
	static int item[];
	static ArrayList<ArrayList<Graph>> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		// 다익스트라
		int a, b, l;
		int max = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		item = new int[n + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			list.add(new ArrayList<>());
			item[i] = Integer.parseInt(st.nextToken()); 
		}
		list.add(new ArrayList<>());
		
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			
			list.get(a).add(new Graph(b, l));
			list.get(b).add(new Graph(a, l));
		}
		
		for(int i = 1; i <= n; i++) 
			max = Math.max(max, sol(i));
		System.out.println(max);
	}

	static int sol(int start) {
		boolean Visited[] = new boolean[n + 1];
		int item_cnt = item[start];
		int city;
		int distance;
		Graph g;
		PriorityQueue<Graph> queue = new PriorityQueue<>();
		Visited[start] = true;
		queue.addAll(list.get(start));
		
		while(!queue.isEmpty()) {
			g = queue.poll();
			city = g.city;
			distance = g.distance;
			if(Visited[city])
				continue;
			if(distance <= m) 
				item_cnt = item[city] + item_cnt;
			else
				break;
			for(Graph s : list.get(city)) {
				queue.add(new Graph(s.city, s.distance + distance));
			}
			Visited[city] = true;
		}
		
		return item_cnt;
	}
}

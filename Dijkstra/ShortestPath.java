import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ShortestPath {
	static class Vertex implements Comparable<Vertex>{
		int num;
		int distance;
		
		Vertex(int a, int b) {
			num = a;
			distance = b;
		}

		@Override
		public int compareTo(Vertex target) {
			return this.distance - target.distance;
		}
	}
	
	static boolean Visited[];
	static int distance[];
	static ArrayList<PriorityQueue<Vertex>> list = new ArrayList<>();
	static int V;
	static int E;
	public static void main(String[] args) throws IOException {
		// 다익스트라
		int u, v, w;
		int start;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		Visited = new boolean[V + 1];
		distance = new int[V + 1];
		
		start = Integer.parseInt(br.readLine());
		for(int i = 0; i < V + 1; i++) 
			list.add(new PriorityQueue<>());
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			list.get(u).add(new Vertex(v, w));
		}
		
		if(V == 1)
			System.out.println(0);
		else {
			sol(start);
		
			for(int i = 1; i < V + 1; i++) {
				if(distance[i] == 0 && i != start)
					System.out.println("INF");
				else 
					System.out.println(distance[i]);
			}
		}
	}

	static void sol(int start) {
		Vertex v;
		Vertex comp;
		int prev_num;
		int limit;
		PriorityQueue<Vertex> queue = new PriorityQueue<>();
		queue.add(new Vertex(start, 0));
		while(!queue.isEmpty()) {
			prev_num = 0;
			v = queue.poll();
			if(Visited[v.num])
				continue;
			limit = list.get(v.num).size();
			for(int i = 0; i < limit; i++) {
				comp = list.get(v.num).poll();
				if(prev_num == comp.num || Visited[comp.num])
					continue;
				if(distance[comp.num] == 0) 
					distance[comp.num] = comp.distance + distance[v.num];
				else 
					distance[comp.num] = Math.min(distance[comp.num], comp.distance + distance[v.num]);
				queue.add(new Vertex(comp.num, distance[comp.num]));
				prev_num = comp.num;
			}
			Visited[v.num] = true;
		}
	}
}

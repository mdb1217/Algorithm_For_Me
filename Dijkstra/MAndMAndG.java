import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class MAndMAndG {
	static class Vertex implements Comparable<Vertex> {
		int E;
		int Distance;
		boolean save;
		
		Vertex(int a, int b, boolean c)	{
			E = a;
			Distance = b;
			save = c;
		}

		@Override
		public int compareTo(Vertex target) {
			if(this.Distance > target.Distance)
				return 1;
			else if(this.Distance == target.Distance) {
				if(this.save)
					return -1;
				else
					return 1;
			}
			else
				return -1;
		}
	}

	static boolean Visited[];
	static int V, P;
	static ArrayList<Queue<Vertex>> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		// 다익스트라
		int a, b, c;
		int E;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		Visited = new boolean[V + 1];
		for(int i = 0; i < V + 1; i++)
			list.add(new LinkedList<>());
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			list.get(a).add(new Vertex(b, c, false));
			list.get(b).add(new Vertex(a, c, false));
		}
		
		sol();
	}
	
	static void sol() {
		boolean s;
		Vertex v;
		PriorityQueue<Vertex> queue = new PriorityQueue<>();
		
		queue.add(new Vertex(1, 0, false));
		while(!queue.isEmpty()) {
			v = queue.poll();
			s = v.save;
			if(v.E == P)
				s = true;
			if(v.E == V) {
				System.out.println(s? "SAVE HIM" : "GOOD BYE");
				break;
			}
			if(!Visited[v.E]) {
				for(Vertex i : list.get(v.E))
					queue.add(new Vertex(i.E, i.Distance + v.Distance, s));
				Visited[v.E] = true;
			}
		}
	}

}

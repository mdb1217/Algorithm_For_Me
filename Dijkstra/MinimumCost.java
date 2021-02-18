import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MinimumCost  {
	static class Bus implements Comparable<Bus> {
		int city;
		int cost;
		Bus(int a, int c) {
			city = a;
			cost = c;
		}
		
		public int compareTo(Bus target) {
	        return this.cost > target.cost ? 1 : -1;
	    }
	}
	
	static int Visited[];
	static ArrayList<ArrayList<Bus>> list = new ArrayList<>();
	static int min = 100000001;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 다익스트라
		int N;
		int M;
		int start;
		int end;
		int cost;
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		Visited = new int[N + 1];
		
		for(int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			list.get(start).add(new Bus(end, cost));
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		bfs(start, end);
		
		System.out.println(min);
	}

	static void bfs(int start, int end) {
		Bus b;
		PriorityQueue<Bus> queue = new PriorityQueue<>();
		queue.add(new Bus(start, 0));
		
		while(!queue.isEmpty()) {
			b = queue.poll();
			if(Visited[b.city] == 1)
				continue;
			else
				Visited[b.city] = 1;
			for(Bus k : list.get(b.city)) {
				queue.add(new Bus(k.city, k.cost + b.cost));
				if(k.city == end) {
					min = Math.min(min, k.cost + b.cost);
				}
			}
		}
	}
}

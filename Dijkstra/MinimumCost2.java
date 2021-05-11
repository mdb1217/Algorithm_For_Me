import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MinimumCost2 {
	static class Bus implements Comparable<Bus> {
		int city;
		int cost;
		int cnt;
		StringBuilder sb = new StringBuilder();
		
		Bus(int a, int c, int d) {
			city = a;
			cost = c;
			cnt = d;
		}
		
		public int compareTo(Bus target) {
	        return this.cost > target.cost ? 1 : -1;
	    }
	}
	
	static int min = Integer.MAX_VALUE;
	static boolean Visited[];
	static ArrayList<ArrayList<Bus>> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		// 다익스트라
		int start, end, cost;
		int N, M;
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		Visited = new boolean[N + 1];
		for(int i = 0; i < N + 1; ++i)
			list.add(new ArrayList<>());
		
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			list.get(start).add(new Bus(end, cost, 0));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		sol(start, end);
	}

	static void sol(int start, int end) {
		Bus b;
		Bus temp;
		PriorityQueue<Bus> queue = new PriorityQueue<>();
		
		b = new Bus(start, 0, 1);
		b.sb.append(start);
		
		queue.add(b);
		
		while(!queue.isEmpty()) {
			b = queue.poll();
			if(Visited[b.city])
				continue;
			else
				Visited[b.city] = true;
			
			if(b.city == end) {
				System.out.println(b.cost);
				System.out.println(b.cnt);
				System.out.println(b.sb);
				break;
			}
			
			for(Bus k : list.get(b.city)) {
				temp = new Bus(k.city, k.cost + b.cost, b.cnt + 1);
				temp.sb.append(b.sb).append(" ").append(k.city);
				queue.add(temp);
			}
		}
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CupRamen {
	static class Ramen implements Comparable<Ramen> {
		int limit;
		int ramen;
		
		Ramen(int a, int b) {
			limit = a;
			ramen = b;
		}

		@Override
		public int compareTo(Ramen target) {
			if(limit > target.limit) {
				return -1;
			}
			else if(limit == target.limit) {
				if(ramen > target.ramen)
					return -1;
				else if(ramen == target.ramen)
					return 0;
				else
					return 1;
			}
			else
				return 1;
		}
	}

	public static void main(String[] args) throws IOException {
		// 그리디 알고리즘
		int prev;
		Ramen r;
		int N;
		int ans;
		int limit;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PriorityQueue<Ramen> queue = new PriorityQueue<>();
		PriorityQueue<Integer> temp_queue = new PriorityQueue<>(Collections.reverseOrder());
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			
			queue.add(new Ramen(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		r = queue.poll();
		ans = r.ramen;
		prev = r.limit;
		while(!queue.isEmpty()) {
			r = queue.poll();
			if(r.limit == prev)
				temp_queue.add(r.ramen);
			else {
				limit = prev - r.limit - 1;
				while(!temp_queue.isEmpty() && limit > 0) {
					ans += temp_queue.poll();
					--limit;
				}
				temp_queue.add(r.ramen);
				ans += temp_queue.poll();
				
				prev = r.limit;
			}
		}
		
		limit = prev - 1;
		while(!temp_queue.isEmpty() && limit > 0) {
			ans += temp_queue.poll();
			--limit;
		}
		
		System.out.println(ans);
	}

}

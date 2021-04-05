import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class TomorrowDo {
	static class HomeWork implements Comparable<HomeWork> {
		int day;
		int limit;
		
		HomeWork(int a, int b) {
			day = a;
			limit = b;
		}
		
		@Override
		public int compareTo(HomeWork target) {
			return target.limit - limit;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 그리디(끝나는 리미트 기준으로 내림차순 정렬)
		int can_limit = 0;
		HomeWork hw;
		int n;
		int d, t;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PriorityQueue<HomeWork> queue = new PriorityQueue<>();
		
		n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			queue.add(new HomeWork(d, t));
			can_limit = Math.max(can_limit, t);
		}
		
		while(!queue.isEmpty()) {
			hw = queue.poll();
			if(hw.limit < can_limit)
				can_limit = hw.limit - hw.day;
			else
				can_limit = can_limit - hw.day;
		}
		System.out.println(can_limit);
	}

}

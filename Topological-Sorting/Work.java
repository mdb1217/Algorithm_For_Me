import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Work {
	static class Labor implements Comparable<Labor>{
		int num;
		int time;
		
		Labor(int a, int b) {
			num = a;
			time = b;
		}

		@Override
		public int compareTo(Labor target) {
			return time - target.time;
		}
	}

	public static void main(String[] args) throws IOException {
		// 위상 정렬
		PriorityQueue<Labor> queue = new PriorityQueue<>();
		Labor l;
		int limit;
		int require_work_cnt[];
		int time[];
		ArrayList<Queue<Integer>> list = new ArrayList<>();
		int N;
		int ans = 0;
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		time = new int[N + 1];
		require_work_cnt = new int[N + 1];
		
		for(int i = 0; i < N + 1; i++)
			list.add(new LinkedList<>());
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			limit = Integer.parseInt(st.nextToken());
			for(int j = 0; j < limit; j++) {
				list.get(Integer.parseInt(st.nextToken())).add(i);
				++require_work_cnt[i];
			}
			
			if(limit == 0)
				queue.add(new Labor(i, time[i]));
		}
		
		while(!queue.isEmpty()) {
			l = queue.poll();
			ans = l.time;
			for(int i : list.get(l.num)) {
				--require_work_cnt[i];
				if(require_work_cnt[i] == 0)
					queue.add(new Labor(i, l.time + time[i]));
			}
		}
		
		System.out.println(ans);
	}

}

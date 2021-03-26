import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class ACMCraft {
	static class Build implements Comparable<Build> {
		int num;
		int time;
		
		Build(int a, int b) {
			num = a;
			time = b;
		}

		@Override
		public int compareTo(Build target) {
			return time - target.time;
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		// 위상정렬
		StringBuilder sb = new StringBuilder();
		boolean end;
		Build b;
		int T;
		int K, X, Y, N, W;
		ArrayList<Queue<Integer>> list;
		PriorityQueue<Build> queue;
		int require_build_cnt[];
		int time[];
		int ans[];
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			end = false;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			time = new int[N + 1];
			require_build_cnt = new int[N + 1];
			ans = new int[N + 1];
			list = new ArrayList<>();
			queue = new PriorityQueue<>();
			for(int j = 0; j < N + 1; j++)
				list.add(new LinkedList<>());
			
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++)
				time[j] = Integer.parseInt(st.nextToken());
			
			for(int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				X = Integer.parseInt(st.nextToken());
				Y = Integer.parseInt(st.nextToken());
				list.get(X).add(Y);
				++require_build_cnt[Y];
			}
			
			W = Integer.parseInt(br.readLine());
			
			for(int j = 1; j <= N; j++) {
				if(require_build_cnt[j] == 0) {
					ans[j] = time[j];
					if(j == W) {
						end = true;
						sb.append(ans[j] + "\n");
						break;
					}
					queue.add(new Build(j, time[j]));
				}
			}
			
			if(!end) {
				while(!queue.isEmpty()) {
					b = queue.poll();
					for(int num : list.get(b.num)) {
						--require_build_cnt[num];
						if(require_build_cnt[num] == 0) {
							ans[num] = time[num] + ans[b.num];
							if(num == W) {
								end = true;
								sb.append(ans[num] + "\n");
								break;
							}
							queue.add(new Build(num, ans[num]));
						}
					}
					if(end)
						break;
				}
			}
		}
		System.out.println(sb.toString());
	}

}

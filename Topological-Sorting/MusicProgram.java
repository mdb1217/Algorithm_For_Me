import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MusicProgram {

	public static void main(String[] args) throws IOException {
		// 위상정렬
		StringBuilder sb = new StringBuilder();
		int prev_num;
		int cnt = 0;
		Queue<Integer> queue = new LinkedList<>();
		ArrayList<Queue<Integer>> list = new ArrayList<>();
		boolean Visited[][];
		int require_prev_cnt[];
		int N, M;
		int limit, num;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		require_prev_cnt = new int[N + 1];
		Visited = new boolean[N + 1][N + 1];
		
		for(int i = 0; i < N + 1; i++)
			list.add(new LinkedList<>());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			limit = Integer.parseInt(st.nextToken());
			prev_num = 0;
			for(int j = 0; j < limit; j++) {
				num = Integer.parseInt(st.nextToken());
				if(j != 0 && !Visited[prev_num][num]) {
					list.get(prev_num).add(num);
					++require_prev_cnt[num];
					Visited[prev_num][num] = true;
				}
				prev_num = num;
			}
		}
		
		for(int i = 1; i <= N; i++) {
			if(require_prev_cnt[i] == 0)
				queue.add(i);
		}
		
		while(!queue.isEmpty()) {
			++cnt;
			num = queue.poll();
			sb.append(num + "\n");
			for(int i : list.get(num)) {
				--require_prev_cnt[i];
				if(require_prev_cnt[i] == 0)
					queue.add(i);
			}
		}
		
		System.out.print(cnt < N ? 0 : sb.toString());
	}

}

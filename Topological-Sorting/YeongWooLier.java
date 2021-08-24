import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class YeongWooLier {

	public static void main(String[] args) throws IOException {
		// 위상 정렬
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		int build_cnt[];
		int require_build_cnt[];
		boolean can_not_build[];
		int N, M, K;
		int start, end;
		boolean lier = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		build_cnt = new int[N + 1];
		can_not_build = new boolean[N + 1];
		require_build_cnt = new int[N + 1];
		for(int i = 0; i < N + 1; ++i)
			list.add(new ArrayList<>());
		
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			
			list.get(start).add(end);
			++require_build_cnt[end];
			can_not_build[end] = true;
		}
		
		for(int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine());
			if(!lier) {
				start = Integer.parseInt(st.nextToken());
				end = Integer.parseInt(st.nextToken());
				if(start == 1) {
					if(!can_not_build[end]) {
						++build_cnt[end];
						if(build_cnt[end] == 1) {
							for(int build : list.get(end)) {
								--require_build_cnt[build];
								if(require_build_cnt[build] == 0)
									can_not_build[build] = false;
							} 
						}
					}
					else
						lier = true;
				}
				else {
					if(build_cnt[end] > 0) {
						--build_cnt[end];
						if(build_cnt[end] == 0) {
							for(int build : list.get(end)) {
								++require_build_cnt[build];
								can_not_build[build] = true;
							} 
						}
					}
					else
						lier = true;
				}
			}
		}
		
		System.out.println(lier? "Lier!" : "King-God-Emperor");
	}
}

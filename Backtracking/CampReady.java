import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CampReady {
	
	static int N, L, R, X;
	static int list[];
	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		// 백트래킹
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		list = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			list[i] = Integer.parseInt(st.nextToken());
		
		dfs(0, 0, 0, 0, 0);
		System.out.println(cnt);
	}
	
	static void dfs(int start, int min, int max, int sum, int d) {
		int current_min;
		int current_max;
		int current_sum;
		for(int i = start; i < N; i++) {
			if(d == 0) {
				current_min = list[i];
				current_max = list[i];
				dfs(i + 1, current_min, current_max, list[i], d + 1);
			}
			else {
				current_min = Math.min(list[i], min);
				current_max = Math.max(list[i], max);
				current_sum = sum + list[i];
				if(current_sum >= L) {
					if(current_sum <= R) {
						if(current_max - current_min >= X) 
							++cnt;
						dfs(i + 1, current_min, current_max, current_sum, d + 1);
					}
				}
				else 
					dfs(i + 1, current_min, current_max, current_sum, d + 1);
			}
		}
	}

}
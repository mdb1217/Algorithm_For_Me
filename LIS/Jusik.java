import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jusik {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 가장 긴 증가하는 부분수열
		StringBuilder sb = new StringBuilder();
		int T;
		int ans, mid, left, right;
		int A, N, K;
		int cnt;
		int list[];
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= T; ++i) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			cnt = 0;
			list = new int[N + 1];
			
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; ++j) {
				A = Integer.parseInt(st.nextToken());
				if(cnt < K) {
					if(list[cnt] < A) 
						list[++cnt] = A;
					else if(list[cnt] == A) 
						continue;
					else {
						ans = 0;
						left = 1;
						right = cnt;
						
						while(left <= right) {
							mid = (left + right) / 2;
							if(list[mid] < A)
								left = mid + 1;
							else if(list[mid] == A) {
								ans = mid;
								break;
							}
							else {
								right = mid - 1;
								ans = mid;
							}
						}
						list[ans] = A;
					}
				}
			}
			
			sb.append("Case #").append(i).append("\n");
			if(cnt < K)
				sb.append(0).append("\n");
			else
				sb.append(1).append("\n");	
		}
		
		System.out.print(sb);
	}

}

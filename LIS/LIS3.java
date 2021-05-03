import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LIS3 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 가장 긴 증가하는 부분 수열
		int ans;
		int left, right, mid;
		int N;
		int cnt = 0;
		int A;
		int list[];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		list = new int[N + 1];
		list[0] = Integer.MIN_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; ++i) {
			A = Integer.parseInt(st.nextToken());
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
		
		System.out.println(cnt);
	}

}

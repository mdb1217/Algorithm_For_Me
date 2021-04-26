import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LIS5 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 가장 긴 증가하는 부분 수열
		StringBuilder sb = new StringBuilder();
		ArrayList<Integer> ans_list = new ArrayList<>();
		int temp;
		int ans;
		int left, right, mid;
		int N;
		int cnt = 0;
		int A[];
		int list[];
		int index[];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		A = new int[N + 1];
		list = new int[N + 1];
		index = new int[N + 1];
		list[0] = Integer.MIN_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
			if(list[cnt] < A[i]) {
				list[++cnt] = A[i];
				index[i] = cnt;
			}
			else if(list[cnt] == A[i]) {
				index[i] = cnt;
				continue;
			}
			else {
				ans = 0;
				left = 1;
				right = cnt;
				
				while(left <= right) {
					mid = (left + right) / 2;
					
					if(list[mid] < A[i])
						left = mid + 1;
					else if(list[mid] == A[i]) {
						ans = mid;
						break;
					}
					else {
						right = mid - 1;
						ans = mid;
					}
				}
				list[ans] = A[i];
				index[i] = ans;
			}
		}
		
		System.out.println(cnt);
		temp = cnt;
		for(int i = N; i >= 1; --i) {
			if(index[i] == temp) {
				ans_list.add(A[i]);
				--temp;
			}
			
			if(temp < 1)
				break;
		}
		
		for(int i = ans_list.size() - 1; i >= 0; --i)
			sb.append(ans_list.get(i)).append(" ");
		
		System.out.print(sb);
	}

}

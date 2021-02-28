import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class InterestingBox {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 이분 탐색
		int N;
		int left;
		int right;
		int mid;
		int box;
		int ans;
		int cnt; 
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		right = (N / 2) - 1;
		cnt = N;
		
		for(int i = 0; i < N; i++)
			list.add(Integer.parseInt(br.readLine()));
		
		Collections.sort(list);
		for(int i = N - 1; i >= (N / 2); i--) {//절반까지만~
			box = list.get(i);
			left = 0;
			if(right < 0)
				break;
			ans = -1;
			while(left <= right) {
				mid = (left + right) / 2;
				
				if(list.get(mid) * 2 <= box) {
					ans = mid;
					left = mid + 1;
				}
				else 
					right = mid - 1;
			}
			
			if(ans != -1) {
				--cnt;
				right = ans - 1;
			}
			else 
				break;
		}
		System.out.println(cnt);
	}

}

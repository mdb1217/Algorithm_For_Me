import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Rising {

	public static void main(String[] args) throws IOException {
		// LIS
		int ans;
		int left, right, mid;
		int N;
		int A;
		int cnt;
		int list[];
		String line;
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while((line = br.readLine()) != null) {
			line = line.trim();
			if (line == "" || line.length() == 0)
                break;
			cnt = 0;
			N = Integer.parseInt(line);
			st = new StringTokenizer(br.readLine().trim());
			list = new int[N + 1];
			list[0] = 0;
			for(int i = 0; i < N; ++i) {
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
}

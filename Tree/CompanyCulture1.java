import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CompanyCulture1 {
	
	public static void main(String[] args) throws IOException {
		// Æ®¸®
		int list[];
		int ans[];
		StringBuilder sb = new StringBuilder();
		int n, m;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		list = new int[n + 1];
		ans = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		
		
		for(int i = 1; i <= n; ++i)
			list[i] = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			
			ans[Integer.parseInt(st.nextToken())] += Integer.parseInt(st.nextToken());
		}
		
		sb.append(ans[1]).append(" ");
		for(int i = 2; i <= n; ++i) {
			ans[i] += ans[list[i]];
			sb.append(ans[i]).append(" ");
		}
		
		System.out.println(sb);
	}
}

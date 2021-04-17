import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DiminishNum {

	static StringBuilder sb = new StringBuilder();
	static boolean find = false;
	static int cnt = -1;
	static int N;
	static int limit = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 백트래킹
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		while(!find) {
			++limit;
			if(limit - 1 >= 10) {
				System.out.println(-1);
				break;
			}
			tracking(limit - 1, 0, 10);
		}
	}
	
	static void tracking(int start, int depth, int prev) {
		if(depth == limit) {
			++cnt;
			if(cnt == N) {
				find = true;
				System.out.println(sb);
			}
		}
		else {
			for(int i = start; i <= 9; ++i) {
				if(i >= prev)
					break;
				sb.append(i);
				tracking(start - 1, depth + 1, i);
				if(find)
					break;
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}

}

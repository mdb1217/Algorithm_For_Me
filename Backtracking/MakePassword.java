import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MakePassword {

	static StringBuilder ans = new StringBuilder();
	static StringBuilder sb = new StringBuilder();
	static int list[] = new int[26];
	static int L, C;
	public static void main(String[] args) throws IOException {
		// 백트래킹
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; ++i)
			++list[st.nextToken().charAt(0) - 'a'];
		
		tracking(0, 0, 0, false);
		System.out.print(ans);
	}

	static void tracking(int depth, int start, int jaum_cnt, boolean moum) {
		if(depth == L) {
			if(moum && jaum_cnt >= 2)
				ans.append(sb + "\n");
		}
		else {
			for(int i = start; i < 26; ++i) {
				if(list[i] > 0) {
					--list[i];
					sb.append((char) (i + 'a'));
					if(i == 0 || i == 'e' - 'a' || i == 'i' - 'a' || i == 'o' - 'a' || i == 'u' - 'a')
						tracking(depth + 1, i + 1, jaum_cnt, true);
					else
						tracking(depth + 1, i + 1, jaum_cnt + 1, moum);
					sb.deleteCharAt(sb.length() - 1);
					++list[i];
				}
			}
		}
	}
}

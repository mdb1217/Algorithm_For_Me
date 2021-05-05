import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WorkRecord {

	static boolean Visited[][][][][];
	static StringBuilder sb = new StringBuilder();
	static boolean success = false;
	static int limit;
	static int list[] = new int[3];
	public static void main(String[] args) throws IOException {
		// 백트래킹
		String s;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		s = br.readLine();
		limit = s.length();
		
		for(int i = 0; i < limit; ++i)
			++list[s.charAt(i) - 'A'];
		
		Visited = new boolean[list[0] + 1][list[1] + 1][list[2] + 1][4][4];
		tracking(0, 0, 0, 0, '@', '@');
		System.out.println(success? sb : -1);
	}

	static int tracking(int d, int a, int b, int c, char prev, char pprev) {
		if(d == limit) {
			success = true;
			return 0;
		}
		else {
			if(Visited[a][b][c][prev - 'A' + 1][pprev - 'A' + 1])
				return 0;
			
			Visited[a][b][c][prev - 'A' + 1][pprev - 'A' + 1] = true;
			if(a < list[0]) {
				sb.append("A");
				tracking(d + 1, a + 1, b, c, 'A', prev);
				if(success)
					return 0;
				sb.deleteCharAt(sb.length() - 1);
			}
			if(b < list[1]) {
				if(prev != 'B') {
					sb.append("B");
					tracking(d + 1, a, b + 1, c, 'B', prev);
					if(success)
						return 0;
					sb.deleteCharAt(sb.length() - 1);
				}
			}
			if(c < list[2]) {
				if(prev != 'C' && pprev != 'C') {
					sb.append("C");
					tracking(d + 1, a, b, c + 1, 'C', prev);
					if(success)
						return 0;
					sb.deleteCharAt(sb.length() - 1);
				}
			}
		}
		return 0;
	}
}

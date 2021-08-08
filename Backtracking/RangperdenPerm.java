import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RangperdenPerm {

	static int cnt = 0;
	static int n, x, y;
	static boolean Visited[];
	static int list[];
	public static void main(String[] args) throws IOException {
		// 백트래킹
		int same;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		
		Visited = new boolean[n + 1];
		list = new int[(2 * n) + 1];
		same = y - x - 1;
		
		if(same == 0)
			System.out.println(0);
		else {
			Visited[same] = true;
			list[x] = same;
			list[y] = same;
		
			if(x == 1)
				tracking(2, 1);
			else
				tracking(1, 1);
			System.out.println(cnt);
		}
	}

	public static void tracking(int start, int d) {
		if(d == n)
			++cnt;
		else {
			for(int i = 1; i <= n; ++i) {
				if(start + i + 1 > 2 * n)
					break;
				if(!Visited[i] && list[start + i + 1] == 0) {
					int next;
					list[start] = i;
					list[start + i + 1] = i;
					Visited[i] = true;
					for(next = start + 1; next <= 2 * n; ++next) {
						if(list[next] == 0)
							break;
					}
					tracking(next, d + 1);
					list[start] = 0;
					list[start + i + 1] = 0;
					Visited[i] = false;
				}
			}
		}
	}
}

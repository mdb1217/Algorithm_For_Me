import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SchoolRoad {
	static int w, h;
	static long memo[][];
	public static void main(String[] args) throws IOException {
		// DP 2번해주고 곱해주면 될듯..
		int toast_x;
		int toast_y;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		memo = new long[h + 1][w + 1];
		st = new StringTokenizer(br.readLine());
		toast_y = Integer.parseInt(st.nextToken());
		toast_x = Integer.parseInt(st.nextToken());
		
		System.out.println((DP(toast_x, toast_y, 1, 1) * DP(h, w, toast_x, toast_y)) % 1000007);
	}

	static long DP(int x, int y, int start_x, int start_y) {
		if(x == start_x || y == start_y) 
			return 1;
		else if(memo[x][y] > 0)
			return memo[x][y];
		else {
			memo[x][y] = (DP(x - 1, y, start_x, start_y) + DP(x, y - 1, start_x, start_y)) % 1000007; 
			return memo[x][y];
		}
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GreedyPanda {

	static int n;
	static int op_x[] = {0, 0, 1, -1};
	static int op_y[] = {1, -1, 0, 0};
	static boolean complete[][];
	static int sum[][];
	static int map[][];
	public static void main(String[] args) throws IOException {
		// dfs
		int max = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		sum = new int[n][n];
		complete = new boolean[n][n];
		
		for(int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < n; ++j)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < n; ++j) {
				if(!complete[i][j]) 
					max = Math.max(dfs(i, j, map[i][j]), max);
			}
		}
		
		System.out.println(max);
	}

	static int dfs(int x, int y, int prev) {
		int temp_x, temp_y;
		int max = 0;
		complete[x][y] = true;
		
		if(sum[x][y] > 0)
			return sum[x][y];
		
		for(int i = 0; i < 4; ++i) {
			temp_x = x + op_x[i];
			temp_y = y + op_y[i];
			
			if(temp_x < n && temp_y < n && temp_x >= 0 && temp_y >= 0) {
				if(map[temp_x][temp_y] > prev)
					max = Math.max(dfs(temp_x, temp_y, map[temp_x][temp_y]), max);
			}
		}
		
		sum[x][y] = max + 1;
		return sum[x][y];
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Downhill {
	
	static int op_x[] = {1,-1,0,0};
	static int op_y[] = {0,0,1,-1};
	static int M, N;
	static int Map[][];
	static boolean Visited[][];
	static int memo[][];
	public static void main(String[] args) throws IOException {
		// DP
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		memo = new int[M][N];
		Map = new int[M][N];
		Visited = new boolean[M][N];
		
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; ++j)
				Map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		memo[0][0] = 1;
		
		System.out.println(DP(M - 1, N - 1));
	}
	
	static int DP(int x, int y) {
		int op_X, op_Y;
		int sum = 0;
		if(memo[x][y] > 0)
			return memo[x][y];
		if(Visited[x][y])
			return memo[x][y];
		for(int i = 0; i < 4; ++i) {
			op_X = x + op_x[i];
			op_Y = y + op_y[i];
			if(op_X >= 0 && op_Y >= 0 && op_X < M && op_Y < N && Map[op_X][op_Y] > Map[x][y])
				sum += DP(op_X, op_Y);
		}
		memo[x][y] = sum;
		Visited[x][y] = true;
		return memo[x][y];
	}
}
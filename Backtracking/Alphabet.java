import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Alphabet {

	static int max = 0;
	static int R, C;
	static String Map[];
	static boolean Visited[][];
	static boolean alpha[] = new boolean[26];
	static int op_X[] = {1, -1, 0, 0};
	static int op_Y[] = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		// 백트래킹
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		Map = new String[R];
		Visited = new boolean[R][C];
		
		for(int i = 0; i < R; i++)
			Map[i] = br.readLine();
		
		dfs(0, 0, 1);
		System.out.println(max);
	}

	static void dfs(int x, int y, int d) {
		int op_x, op_y;
		boolean can_move = false;
		Visited[x][y] = true;
		alpha[Map[x].charAt(y) - 'A'] = true;
		for(int i = 0; i < 4; i++) {
			op_x = x + op_X[i];
			op_y = y + op_Y[i];
			
			if(op_x >= 0 && op_y >= 0 && op_x < R && op_y < C) {
				if(!Visited[op_x][op_y] && !alpha[Map[op_x].charAt(op_y) - 'A']) {
					dfs(op_x, op_y, d + 1);
					can_move = true;
				}
			}
		}
		Visited[x][y] = false;
		alpha[Map[x].charAt(y) - 'A'] = false;
		
		if(!can_move)
			max = Math.max(d, max);
	}
}

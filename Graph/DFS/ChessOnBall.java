import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class ChessOnBall {
	static class Graph {
		int x;
		int y;
		
		Graph(int a, int b) {
			x = a;
			y = b;
		}
	}
	
	static int R, C;
	static Graph min_list[][];
	static int Map[][];
	static int num[][];//공개수
	static int op_x[] = {0,0,1,-1,1,1,-1,-1};//동서남북대각선
	static int op_y[] = {1,-1,0,0,1,-1,1,-1};//동서남북대각선
	public static void main(String[] args) throws IOException {
		// dfs
		// 메모이제이션
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		Map = new int[R][C];
		num = new int[R][C];
		min_list = new Graph[R][C];
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
				num[i][j] = 1;				
			}
		}
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(num[i][j] > 0)
					dfs(i, j);
			}
		}
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(num[i][j] + " ");
			}
			System.out.println();
		}
	}

	static void dfs(int start_x, int start_y) {
		int min = 300001;
		int next_x = start_x, next_y = start_y;
		int op_X, op_Y;
		if(min_list[start_x][start_y] != null) {
			next_x = min_list[start_x][start_y].x;
			next_y = min_list[start_x][start_y].y;
			if(next_x != start_x || next_y != start_y) {
				num[next_x][next_y] = num[start_x][start_y] + num[next_x][next_y];
				num[start_x][start_y] = 0;
			}
		}
			
		else {
			for(int i = 0; i < 8; i++) {
				op_X = op_x[i] + start_x; 
				op_Y = op_y[i] + start_y;
				if(op_X >= 0 && op_X < R && op_Y >= 0 && op_Y < C) {
					if(Map[op_X][op_Y] < Map[start_x][start_y]) {
						if(Map[op_X][op_Y] < min) {
							min = Map[op_X][op_Y];
							next_x = op_X;
							next_y = op_Y;
						}
					}
				}
			}
	
			if(next_x != start_x || next_y != start_y) {
				num[next_x][next_y] = num[start_x][start_y] + num[next_x][next_y];
				num[start_x][start_y] = 0;
				dfs(next_x, next_y);
				min_list[start_x][start_y] = min_list[next_x][next_y];
			}
			else {
				min_list[start_x][start_y] = new Graph(start_x, start_y);
			}
		}
	}
}

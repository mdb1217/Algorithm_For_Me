import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FineDust {
	
	static class Graph {
		int x;
		int y;
		int dust;
		
		Graph(int a, int b, int c) {
			x = a;
			y = b;
			dust = c;
		}
	}
	
	static ArrayList<Graph> List = new ArrayList<>();
	static int Map[][];
	static int op_x[] = {0,0,1,-1};//동서남북
	static int op_y[] = {1,-1,0,0};//동서남북
	static int R, C;
	static Graph cleaner_top;//-1좌표
	static Graph cleaner_bottom;//-1좌표
	public static void main(String[] args) throws IOException {
		// bfs? 일단 이렇게 접근
		// 1 : 미세먼지 확산 bfs
		// 2 : 공기청정기 돌기()
		int T;
		int a;
		int c = 0;
		int sum = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		Map = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				a = Integer.parseInt(st.nextToken());
				Map[i][j] = a;
				if(a >= 5)
					List.add(new Graph(i, j, a));
				else {
					if(c < 2) {
						if(a == -1) {
							if(c == 0) {
								cleaner_top = new Graph(i, j, a);
							}
							else
								cleaner_bottom = new Graph(i, j, a);
							++c;
						}
					}
				}
			}
		}
		
		for(int i = 0; i < T; i++) {
			dustBfs();
			clean();
			sum = listChange();
		}
		
		System.out.println(sum + 2);
	}
	
	static void dustBfs() {
		ArrayList<Graph> list = List;
		int x = 0, y = 0;
		int op_X, op_Y; 
		int cnt;
		int dust;
		
		while(!list.isEmpty()) {
			cnt = 0;
			x = list.get(0).x;
			y = list.get(0).y;
			dust = list.get(0).dust;
			list.remove(0);
			for(int i = 0; i < 4; i++) {
				op_X = op_x[i] + x; 
				op_Y = op_y[i] + y;
				if(op_X >= 0 && op_X < R && op_Y >= 0 && op_Y < C && Map[op_X][op_Y] != -1) {
					++cnt;
					Map[op_X][op_Y] = Map[op_X][op_Y] + (dust / 5);
				}
			}
			Map[x][y] = Map[x][y] - ((dust / 5) * cnt);
		}
	}
	
	static void clean() {
		//바람 순환 위쪽
		Map[cleaner_top.x - 1][0] = 0;
		for(int i = cleaner_top.x - 2; i >= 0; i--) {
			Map[i + 1][0] = Map[i][0];
		}
		for(int i = 1; i < C; i++) {
			Map[0][i - 1] = Map[0][i];
		}
		for(int i = 1; i <= cleaner_top.x; i++) {
			Map[i - 1][C - 1] = Map[i][C - 1];
		}
		for(int i = C - 1; i >= 2; i--) {
			Map[cleaner_top.x][i] = Map[cleaner_top.x][i - 1];
		}
		Map[cleaner_top.x][1] = 0;
		
		//바람 순환 아래쪽
		Map[cleaner_bottom.x + 1][0] = 0;
		for(int i = cleaner_bottom.x + 2; i < R; i++) {
			Map[i - 1][0] = Map[i][0];
		}
		for(int i = 1; i < C; i++) {
			Map[R - 1][i - 1] = Map[R - 1][i];
		}
		for(int i = R - 2; i >= cleaner_bottom.x; i--) {
			Map[i + 1][C - 1] = Map[i][C - 1];
		}
		for(int i = C - 1; i >= 2; i--) {
			Map[cleaner_bottom.x][i] = Map[cleaner_bottom.x][i - 1];
		}
		Map[cleaner_bottom.x][1] = 0;
	}
	
	static int listChange() {
		List.clear();
		int sum = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(Map[i][j] >= 5)
					List.add(new Graph(i, j, Map[i][j]));
				sum = sum + Map[i][j];
			}
		}
		return sum;
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class EasyShortestPath {
	static class Graph {
		int x;
		int y;
		int dis;//거리
		
		Graph(int a, int b, int c) {
			x = a;
			y = b;
			dis = c;
		}
	}
	
	static int n, m;
	static int Map[][];
	static int Visited[][];
	static int path_length[][];
	static int op_x[] = {0,0,1,-1};//동서남북
	static int op_y[] = {1,-1,0,0};//동서남북
	public static void main(String[] args) throws IOException {
		// bfs
		// 목표지부터 bfs돌리면 될듯
		int x = 0, y = 0;//목표지 좌표 저장
		int a;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		Map = new int[n][m];
		Visited = new int[n][m];
		path_length = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				a = Integer.parseInt(st.nextToken());
				Map[i][j] = a;
				if(a == 2) {
					x = i;
					y = j;
				}
			}
		}
		
		bfs(x, y);
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(path_length[i][j] != 0) {
					System.out.print(path_length[i][j] + " ");
				}
				else {
					if(Map[i][j] == 0 || Map[i][j] == 2)
						System.out.print(0 + " ");
					else {
						System.out.print(-1 + " ");
					}
				}	
			}
			System.out.println();
		}
	}
	
	static void bfs(int start_x, int start_y) {
		int x = start_x, y = start_y;
		int cnt = 0;
		int op_X, op_Y;
		ArrayList<Graph> list = new ArrayList<>(); 
		list.add(new Graph(x, y, cnt));
		path_length[x][y] = cnt;
		Visited[x][y] = 1;
		
		while(!list.isEmpty()) {
			cnt = list.get(0).dis + 1;
			x = list.get(0).x;
			y = list.get(0).y;
			list.remove(0);
			for(int i = 0; i < 4; i++) {
				op_X = op_x[i] + x; 
				op_Y = op_y[i] + y;
				if(op_X >= 0 && op_X < n && op_Y >= 0 && op_Y < m) {
					if(Map[op_X][op_Y] == 1 && Visited[op_X][op_Y] == 0) {
						list.add(new Graph(op_X, op_Y, cnt));
						Visited[op_X][op_Y] = 1;
						path_length[op_X][op_Y] = cnt;
					}
				}
			}
		}
	}

}

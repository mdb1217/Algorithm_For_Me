import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PrincessSurvive {
	static class Graph {
		int x;
		int y;
		int time;
		boolean sword;
		Graph(int a, int b, int c, boolean d) {
			x = a;
			y = b;
			time = c;
			sword = d;
		}
	}
	
	static boolean success = false;
	static int N, M, T;
	static int Map[][];
	static boolean Visited[][][];//0은 검없을때 1은 검있을때
	static int op_x[] = {0,0,1,-1};//동서남북
	static int op_y[] = {1,-1,0,0};//동서남북
	public static void main(String[] args) throws IOException {
		// bfs
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		Map = new int[N][M];
		Visited = new boolean[N][M][2];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bfs();
		if(!success)
			System.out.println("Fail");
	}
	
	static void bfs() {
		Graph g;
		int x = 0, y = 0;
		int cnt = 0;
		int op_X, op_Y;
		boolean sword;
		Queue<Graph> queue = new LinkedList<>(); 
		queue.add(new Graph(x, y, cnt, false));
		Visited[x][y][0] = true;
		
		while(!queue.isEmpty()) {
			g = queue.poll();
			cnt = g.time + 1;
			if(cnt > T)
				continue;
			x = g.x;
			y = g.y;
			sword = g.sword;
			for(int i = 0; i < 4; i++) {
				op_X = op_x[i] + x; 
				op_Y = op_y[i] + y;
				if(op_X >= 0 && op_X < N && op_Y >= 0 && op_Y < M) {
					if(sword) {
						if(!Visited[op_X][op_Y][1]) {
							if(op_X == N - 1 && op_Y == M - 1) {
								success = true;
								System.out.println(cnt);
								break;
							}
							queue.add(new Graph(op_X, op_Y, cnt, true));
							Visited[op_X][op_Y][1] = true;
						}
					}
					else {
						if(Map[op_X][op_Y] != 1 && !Visited[op_X][op_Y][0]) {
							if(op_X == N - 1 && op_Y == M - 1) {
								success = true;
								System.out.println(cnt);
								break;
							}
							if(Map[op_X][op_Y] != 2) {
								queue.add(new Graph(op_X, op_Y, cnt, false));
								Visited[op_X][op_Y][0] = true;
							}
							else {
								queue.add(new Graph(op_X, op_Y, cnt, true));
								Visited[op_X][op_Y][0] = true;
								Visited[op_X][op_Y][1] = true;
							}
						}
					}
				}
			}
			if(success)
				break;
		}
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class EscapeMaze {
	static class Graph {
		int x;
		int y;
		int cnt;
		boolean destroy;
		Graph(int a, int b, int c, boolean d) {
			x = a;
			y = b;
			cnt = c;
			destroy = d;
		}
	}
	
	static int end_x, end_y;
	static int N, M;
	static boolean success = false;
	static int Map[][];
	static boolean Visited[][][];
	static int op_x[] = {0,0,1,-1};
	static int op_y[] = {1,-1,0,0};
	public static void main(String[] args) throws IOException {
		// bfs
		int start_x, start_y;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		start_x = Integer.parseInt(st.nextToken());
		start_y = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		end_x = Integer.parseInt(st.nextToken());
		end_y = Integer.parseInt(st.nextToken());
		
		Map = new int[N + 1][M + 1];
		Visited = new boolean[N + 1][M + 1][2];
		
		for(int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; ++j)
				Map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		bfs(start_x, start_y);
		
		if(!success)
			System.out.println(-1);
	}
	
	static void bfs(int start_x, int start_y) {
		Graph g;
		int x = 0, y = 0;
		int cnt = 1;
		int op_X, op_Y;
		boolean destroy;
		Queue<Graph> queue = new LinkedList<>(); 
		queue.add(new Graph(start_x, start_y, 0, false));
		Visited[start_x][start_y][0] = true;
		
		while(!queue.isEmpty()) {
			g = queue.poll();
			x = g.x;
			y = g.y;
			cnt = g.cnt + 1;
			destroy = g.destroy;
			for(int i = 0; i < 4; i++) {
				op_X = op_x[i] + x; 
				op_Y = op_y[i] + y;
				if(op_X >= 1 && op_X <= N && op_Y >= 1 && op_Y <= M) {
					if(destroy) {
						if(Map[op_X][op_Y] != 1 && !Visited[op_X][op_Y][1]) {
							if(op_X == end_x && op_Y == end_y) {
								success = true;
								System.out.println(cnt);
								break;
							}
							queue.add(new Graph(op_X, op_Y, cnt, true));
							Visited[op_X][op_Y][1] = true;
						}
					}
					else {
						if(Map[op_X][op_Y] == 1) {
							if(!Visited[op_X][op_Y][1]) {
								if(op_X == end_x && op_Y == end_y) {
									System.out.println("in2");
									success = true;
									System.out.println(cnt);
									break;
								}
								queue.add(new Graph(op_X, op_Y, cnt, true));
								Visited[op_X][op_Y][1] = true;
							}
						}
						else {
							if(!Visited[op_X][op_Y][0]) {
								if(op_X == end_x && op_Y == end_y) {
									success = true;
									System.out.println(cnt);
									break;
								}
								queue.add(new Graph(op_X, op_Y, cnt, false));
								Visited[op_X][op_Y][0] = true;
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

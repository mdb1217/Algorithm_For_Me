import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class WallBreak {
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
	
	static int N, M;
	static boolean success = false;
	static String Map[];
	static boolean Visited[][][];//0은 벽안부쉈을때 1은 벽부쉈을때
	static int op_x[] = {0,0,1,-1};//동서남북
	static int op_y[] = {1,-1,0,0};//동서남북
	public static void main(String[] args) throws IOException {
		// bfs
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new String[N];
		Visited = new boolean[N][M][2];
		for(int i = 0; i < N; i++)
			Map[i] = br.readLine();
		
		if(N == 1 && M == 1)
			System.out.println(1);
		else {
			bfs();
			if(!success)
				System.out.println(-1);
		}
	}
	
	static void bfs() {
		Graph g;
		int x = 0, y = 0;
		int cnt = 1;
		int op_X, op_Y;
		boolean destroy;
		Queue<Graph> queue = new LinkedList<>(); 
		queue.add(new Graph(x, y, cnt, false));
		
		while(!queue.isEmpty()) {
			g = queue.poll();
			x = g.x;
			y = g.y;
			cnt = g.cnt + 1;
			destroy = g.destroy;
			for(int i = 0; i < 4; i++) {
				op_X = op_x[i] + x; 
				op_Y = op_y[i] + y;
				if(op_X >= 0 && op_X < N && op_Y >= 0 && op_Y < M) {
					if(destroy) {//파괴 기회 쓴 경우
						if(Map[op_X].charAt(op_Y) != '1' && !Visited[op_X][op_Y][1]) {
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
						if(Map[op_X].charAt(op_Y) == '1') {
							if(!Visited[op_X][op_Y][1]) {
								queue.add(new Graph(op_X, op_Y, cnt, true));
								Visited[op_X][op_Y][1] = true;
							}
						}
						else {
							if(!Visited[op_X][op_Y][0]) {
								if(op_X == N - 1 && op_Y == M - 1) {
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

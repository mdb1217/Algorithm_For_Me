import java.util.*;
import java.io.*;

public class PipeMove1 {
	static class Graph {
		int x;
		int y;
		int mode;
		
		Graph(int a, int b, int c) {
			x = a;
			y = b;
			mode = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// DP
		int cnt = 0;
		Graph g;
		Queue<Graph> queue = new LinkedList<>();//mode 0 : 가로, 1 : 세로, 2 : 대각선
		int N;
		int Map[][];
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
				Map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		queue.add(new Graph(0, 1, 0));
		if(Map[N - 1][N - 1] == 1)
			System.out.println(0);
		else {
			while(!queue.isEmpty()) {
				g = queue.poll();
				if(g.mode == 0) {//가로
					if(g.y + 1 < N) {
						if(Map[g.x][g.y + 1] != 1) {
							if(g.x == N - 1 && g.y + 1 == N - 1)
								++cnt;
							else
								queue.add(new Graph(g.x, g.y + 1, 0));
						}
					}
					if(g.x + 1 < N && g.y + 1 < N) {
						if(Map[g.x + 1][g.y] != 1 && Map[g.x][g.y + 1] != 1 && Map[g.x + 1][g.y + 1] != 1) {
							if(g.x + 1 == N - 1 && g.y + 1 == N - 1)
								++cnt;
							else
								queue.add(new Graph(g.x + 1, g.y + 1, 2));
						}
					}
				}
				else if(g.mode == 1) {
					if(g.x + 1 < N) {
						if(Map[g.x + 1][g.y] != 1) {
							if(g.x + 1 == N - 1 && g.y == N - 1)
								++cnt;
							else
								queue.add(new Graph(g.x + 1, g.y, 1));
						}
					}
					if(g.x + 1 < N && g.y + 1 < N) {
						if(Map[g.x + 1][g.y] != 1 && Map[g.x][g.y + 1] != 1 && Map[g.x + 1][g.y + 1] != 1) {
							if(g.x + 1 == N - 1 && g.y + 1 == N - 1)
								++cnt;
							else
								queue.add(new Graph(g.x + 1, g.y + 1, 2));
						}
					}
				}
				else {
					if(g.y + 1 < N) {
						if(Map[g.x][g.y + 1] != 1) {
							if(g.x == N - 1 && g.y + 1 == N - 1)
								++cnt;
							else
								queue.add(new Graph(g.x, g.y + 1, 0));
						}
					}
					if(g.x + 1 < N) {
						if(Map[g.x + 1][g.y] != 1) {
							if(g.x + 1 == N - 1 && g.y == N - 1)
								++cnt;
							else
								queue.add(new Graph(g.x + 1, g.y, 1));
						}
					}
					if(g.x + 1 < N && g.y + 1 < N) {
						if(Map[g.x + 1][g.y] != 1 && Map[g.x][g.y + 1] != 1 && Map[g.x + 1][g.y + 1] != 1) {
							if(g.x + 1 == N - 1 && g.y + 1 == N - 1)
								++cnt;
							else
								queue.add(new Graph(g.x + 1, g.y + 1, 2));
						}
					}
				}
			}
			System.out.println(cnt);
		}
	}

}

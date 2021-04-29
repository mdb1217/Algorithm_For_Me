import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class RabbitsGoInfoIsland {
	static class Graph {
		int x;
		int y;
		int cnt;
		
		Graph(int a, int b, int c) {
			x = a;
			y = b;
			cnt = c;
		}
	}

	public static void main(String[] args) throws IOException {
		// DP(╧ыер╬В)
		int op_x[] = {1, 0, -1};
		int op_y[] = {1, 1, 1};
		Graph g;
		int temp_x, temp_y;
		Queue<Graph> queue = new LinkedList<>();
		Graph rabbit;
		String s;
		int O_cnt = 0; 
		int max = -1;
		int N, M;
		int Visited[][];
		char Map[][];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new char[N][M];
		Visited = new int[N][M];
		for(int i = 0; i < N; ++i) {
			s = br.readLine();
			for(int j = 0; j < M; ++j) {
				Map[i][j] = s.charAt(j);
				Visited[i][j] = -1;
				
				if(Map[i][j] == 'R') {
					rabbit = new Graph(i, j, 0);
					queue.add(rabbit);
				}
				
				if(Map[i][j] == 'O')
					++O_cnt;
			}
		}
		
		if(O_cnt != 0) {
			while(!queue.isEmpty()) {
				g = queue.poll();
				
				if(Visited[g.x][g.y] > g.cnt)
					continue;
				
				for(int i = 0; i < 3; ++i) {
					temp_x = g.x + op_x[i];
					temp_y = g.y + op_y[i];
					
					if(temp_x >= 0 && temp_x < N && temp_y >= 0 && temp_y < M) {
						if(Map[temp_x][temp_y] != '#') {
							if(Map[temp_x][temp_y] == 'O') {
								if(Visited[temp_x][temp_y] < g.cnt) {
									Visited[temp_x][temp_y] = g.cnt;
									max = Math.max(g.cnt, max);
									
									queue.add(new Graph(temp_x, temp_y, g.cnt));
								}
							}
							else {
								if(Visited[temp_x][temp_y] < g.cnt) {
									Visited[temp_x][temp_y] = g.cnt;
									
									if(Map[temp_x][temp_y] == 'C')
										queue.add(new Graph(temp_x, temp_y, g.cnt + 1));
									else
										queue.add(new Graph(temp_x, temp_y, g.cnt));
								}
							}
						}
					}
				}
			}
		}
		System.out.println(max);
	}
}

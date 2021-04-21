import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BreakWallandMove4 {
	static class Graph {
		int x;
		int y;
		
		Graph(int a, int b) {
			x = a;
			y = b;
		}
	}
	
	static ArrayList<Integer> area_size_list = new ArrayList<>();
	static int Visited[][];
	static char Map[][];
	static int N, M;
	static int op_x[] = {1, -1, 0, 0};
	static int op_y[] = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		// bfs
		ArrayList<Integer> list;
		int cnt;
		StringBuilder sb = new StringBuilder();
		int temp_x, temp_y;
		int area = 0;
		String s;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Visited = new int[N][M];
		Map = new char[N][M];
		area_size_list.add(0);
		
		for(int i = 0; i < N; ++i) {
			s = br.readLine();
			for(int j = 0; j < M; ++j)
				Map[i][j] = s.charAt(j);
		}
		
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < M; ++j) {
				if(Map[i][j] == '0' && Visited[i][j] == 0) {
					++area;
					area_size_list.add(bfs(i, j, area));
				}
			}
		}
		
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < M; ++j) {
				if(Map[i][j] == '1') {
					list = new ArrayList<>();
					cnt = 0;
					for(int k = 0; k < 4; ++k) {
						temp_x = i + op_x[k];
						temp_y = j + op_y[k];
						
						if(temp_x >= 0 && temp_x < N && temp_y >= 0 && temp_y < M) {
							if(!list.contains(Visited[temp_x][temp_y])) {
								list.add(Visited[temp_x][temp_y]);
								cnt += area_size_list.get(Visited[temp_x][temp_y]);
							}
						}
					}
					sb.append((cnt + 1) % 10);
				}
				else
					sb.append(0);
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}

	static int bfs(int x, int y, int area) {
		int temp_x, temp_y;
		int cnt = 0;
		Graph g;
		Queue<Graph> queue = new LinkedList<>();
		
		queue.add(new Graph(x, y));
		Visited[x][y] = area;
		
		while(!queue.isEmpty()) {
			g = queue.poll();
			++cnt;
			
			for(int i = 0; i < 4; ++i) {
				temp_x = g.x + op_x[i];
				temp_y = g.y + op_y[i];
				
				if(temp_x >= 0 && temp_x < N && temp_y >= 0 && temp_y < M) {
					if(Map[temp_x][temp_y] == '0' && Visited[temp_x][temp_y] == 0) {
						Visited[temp_x][temp_y] = area;
						queue.add(new Graph(temp_x, temp_y));
					}
				}
			}
		}
		
		return cnt;
	}
}

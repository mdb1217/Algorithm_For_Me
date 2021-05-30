import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class GreenClothIsZelda {
	static class Graph {
		int x;
		int y;
		
		Graph(int a, int b) {
			x = a;
			y = b;
		}
	}
	
	static int N;
	static int map[][];
	static int min[][];
	public static void main(String[] args) throws IOException {
		// 다익스트라, bfs
		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while((N = Integer.parseInt(br.readLine())) != 0) {
			map = new int[N][N];
			min = new int[N][N];
			++cnt;
			
			for(int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
			
				Arrays.fill(min[i], Integer.MAX_VALUE);
				for(int j = 0; j < N; ++j)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			min[0][0] = map[0][0];
			bfs();
			
			sb.append("Problem ").append(cnt).append(": ").append(min[N - 1][N - 1]).append("\n");
		}
		
		System.out.print(sb);
	}

	static void bfs() {
		Graph g;
		int temp_x, temp_y;
		int op_x[] = {0, 0, 1, -1};
		int op_y[] = {1, -1, 0, 0};
		Queue<Graph> queue = new LinkedList<>();
		
		queue.add(new Graph(0, 0));
		
		while(!queue.isEmpty()) {
			g = queue.poll();
			for(int i = 0; i < 4; ++i) {
				temp_x = g.x + op_x[i];
				temp_y = g.y + op_y[i];
				
				if(temp_x < N && temp_y < N && temp_x >= 0 && temp_y >= 0) {
					if(min[temp_x][temp_y] > min[g.x][g.y] + map[temp_x][temp_y]) {
						min[temp_x][temp_y] = min[g.x][g.y] + map[temp_x][temp_y];
						queue.add(new Graph(temp_x, temp_y));
					}
				}
			}
		}
	}
}

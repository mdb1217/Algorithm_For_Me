import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class AlgoSpot {
	static class Graph {
		int x;
		int y;
		
		Graph(int a, int b) {
			x = a;
			y = b;
		}
	}
	
	static int M, N;
	static String map[];
	static int min[][];
	public static void main(String[] args) throws IOException {
		// bfs
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new String[N];
		min = new int[N][M];
		
		for(int i = 0; i < N; ++i) {
			map[i] = br.readLine();
			Arrays.fill(min[i], 10001);
		}
		min[0][0] = 0;
		bfs();
		
		System.out.println(min[N - 1][M - 1]);
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
				
				if(temp_x < N && temp_y < M && temp_x >= 0 && temp_y >= 0) {
					if(map[temp_x].charAt(temp_y) == '0') {
						if(min[temp_x][temp_y] > min[g.x][g.y]) {
							min[temp_x][temp_y] = min[g.x][g.y];
							queue.add(new Graph(temp_x, temp_y));
						}
					}
					else {
						if(min[temp_x][temp_y] > min[g.x][g.y] + 1) {
							min[temp_x][temp_y] = min[g.x][g.y] + 1;
							queue.add(new Graph(temp_x, temp_y));
						}
					}
				}
			}
		}
	}
}

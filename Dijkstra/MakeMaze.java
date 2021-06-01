import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MakeMaze {
	static class Graph {
		int x;
		int y;
		
		Graph(int a, int b) {
			x = a;
			y = b;
		}
	}
	
	static int N;
	static String map[];
	static int min[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 다익스트라
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new String[N];
		min = new int[N][N];
		for(int i = 0; i < N; ++i) {
			Arrays.fill(min[i], Integer.MAX_VALUE);
			map[i] = br.readLine();
		}
		
		bfs();
		
		System.out.println(min[N - 1][N - 1]);
	}

	static void bfs() {
		Graph g;
		int temp_x, temp_y;
		int op_x[] = {0, 0, 1, -1};
		int op_y[] = {1, -1, 0, 0};
		Queue<Graph> queue = new LinkedList<>();
		
		if(map[0].charAt(0) == '1')
			min[0][0] = 0;
		else
			min[0][0] = 1;
		
		queue.add(new Graph(0, 0));
		while(!queue.isEmpty()) {
			g = queue.poll();
			for(int i = 0; i < 4; ++i) {
				temp_x = g.x + op_x[i];
				temp_y = g.y + op_y[i];
				
				if(temp_x < N && temp_y < N && temp_x >= 0 && temp_y >= 0) {
					if(map[temp_x].charAt(temp_y) == '1') {
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

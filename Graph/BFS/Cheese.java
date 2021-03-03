import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Cheese {
	static class Graph {
		int x;
		int y;
		
		Graph(int a, int b) {
			x = a;
			y = b;
		}
	}

	static int N, M;
	static int Map[][];
	static boolean Air[][];
	static int op_X[] = {1, -1, 0, 0};//동서남북
	static int op_Y[] = {0, 0, 1, -1};
	static Queue<Graph> air_queue = new LinkedList<>();
	static Queue<Graph> cheese_queue = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		// 맨 가장자리에는 치즈가 없음.
		// 1. 공기들을 BFS로 다 찾아줌.
		// 2. 공기 인접 치즈를 녹게함.
		// 1-2 반복
		int time = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Map = new int[N][M];
		Air = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
				if(Map[i][j] == 1)
					cheese_queue.add(new Graph(i, j));
			}
		}
		Air[0][0] = true;
		air_queue.add(new Graph(0, 0));
		
		while(cheese_queue.size() > 0) {
			air_bfs();
			cheese_bfs();
			++time;
		}
		System.out.println(time);
	}
	
	static void air_bfs() {
		Graph g;
		int x;
		int y;
		int op_x, op_y;
		while(!air_queue.isEmpty()) {
			g = air_queue.poll();
			x = g.x;
			y = g.y;
			Map[x][y] = 0;
			for(int i = 0; i < 4; i++) {
				op_x = x + op_X[i];
				op_y = y + op_Y[i];
				
				if(op_x >= 0 && op_x < N && op_y >= 0 && op_y < M) {
					if(Map[op_x][op_y] == 0 && !Air[op_x][op_y]) {
						Air[op_x][op_y] = true;
						air_queue.add(new Graph(op_x, op_y));
					}
				}
			}
		}
	}
	
	static void cheese_bfs() {
		Graph g;
		int x;
		int y;
		int air_cnt;
		int limit = cheese_queue.size();
		int op_x, op_y;
		for(int i = 0; i < limit; i++) {
			air_cnt = 0;
			g = cheese_queue.poll();
			x = g.x;
			y = g.y;
			
			for(int j = 0; j < 4; j++) {
				op_x = x + op_X[j];
				op_y = y + op_Y[j];
				
				if(Map[op_x][op_y] == 0 && Air[op_x][op_y])
					++air_cnt;
			}
			
			if(air_cnt >= 2) {
				Air[x][y] = true;
				air_queue.add(new Graph(x, y));
			}
			else 
				cheese_queue.add(new Graph(x, y));
		}
	}

}

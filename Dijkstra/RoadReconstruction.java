import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class RoadReconstruction {
	static class Graph implements Comparable<Graph>{
		int x;
		int y;
		int distance;
		
		Graph(int a, int b, int d) {
			x = a;
			y = b;
			distance = d;
		}

		@Override
		public int compareTo(Graph target) {
			return distance - target.distance;
		}
	}
	
	static int M, N;
	static int map[][];
	static boolean Visited[][];
	public static void main(String[] args) throws IOException {
		// 다익스트라
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		Visited = new boolean[M][N];
		
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; ++j)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		if(map[M - 1][N - 1] != -1)
			bfs();
		
		if(!Visited[M - 1][N - 1])
			System.out.println(-1);
	}
	
	static void bfs() {
		Graph g;
		int temp_x, temp_y;
		int op_x[] = {0, 0, 1, -1};
		int op_y[] = {1, -1, 0, 0};
		PriorityQueue<Graph> queue = new PriorityQueue<>();
		
		if(map[0][0] != -1)
			queue.add(new Graph(0, 0, map[0][0]));
		
		while(!queue.isEmpty()) {
			g = queue.poll();
			if(Visited[g.x][g.y])
				continue;
			Visited[g.x][g.y] = true;
			
			if(g.x == M - 1 && g.y == N - 1) {
				System.out.println(g.distance);
				break;
			}
			
			for(int i = 0; i < 4; ++i) {
				temp_x = g.x + op_x[i];
				temp_y = g.y + op_y[i];
				
				if(temp_x < M && temp_y < N && temp_x >= 0 && temp_y >= 0 && map[temp_x][temp_y] != -1 && !Visited[temp_x][temp_y])
					queue.add(new Graph(temp_x, temp_y, g.distance + map[temp_x][temp_y]));
			}
		}
	}
}

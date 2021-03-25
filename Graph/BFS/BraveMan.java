import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BraveMan {
	static class Graph implements Comparable<Graph> {
		int x;
		int y;
		int L;
		int R;
		
		Graph(int a, int b, int c, int d) {
			x = a;
			y = b;
			L = c;
			R = d;
		}

		@Override
		public int compareTo(Graph target) {
			if((L + R) > (target.L + target.R)) {
				return 1;
			}
			else
				return -1;
		}
		
	}
	
	static PriorityQueue<Graph> queue = new PriorityQueue<>();
	static char Map[][];
	static boolean Visited[][];
	static int op_X[] = {1, -1, 0, 0};
	static int op_Y[] = {0, 0, 1, -1};
	static int L, R;
	static int N, M;
	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		// bfs
		String s;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		Map = new char[N][M];
		Visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			s = br.readLine();
			
			for(int j = 0; j < M; j++) {
				Map[i][j] = s.charAt(j);
				if(Map[i][j] == '2') {
					queue.add(new Graph(i, j, 0, 0));
					Visited[i][j] = true;
					++cnt;
				}
			}
		}
		
		bfs();
		System.out.println(cnt);
		
	}

	static void bfs() {
		Graph g;
		int x, y;
	
		while(!queue.isEmpty()) {
			g = queue.poll();
			x = g.x - 1;
			y = g.y;
			while(x >= 0) {//위로
				if(!Visited[x][y] && Map[x][y] != '1') {
					Visited[x][y] = true;
					queue.add(new Graph(x, y, g.L, g.R));
					++cnt;
				}
				else
					break;
				x = x - 1;
			}
			x = g.x + 1;
			
			while(x < N) {//아래
				if(!Visited[x][y] && Map[x][y] != '1') {
					Visited[x][y] = true;
					queue.add(new Graph(x, y, g.L, g.R));
					++cnt;
				}
				else
					break;
				x = x + 1;
			}
			
			x = g.x;
			if(g.L < L) {
				if(y - 1 >= 0) {
					if(!Visited[x][y - 1] && Map[x][y - 1] != '1') {
						Visited[x][y - 1] = true;
						queue.add(new Graph(x, y - 1, g.L + 1, g.R));
						++cnt;
					}
				}
			}
			
			if(g.R < R) {
				if(y + 1 < M) {
					if(!Visited[x][y + 1] && Map[x][y + 1] != '1') {
						Visited[x][y + 1] = true;
						queue.add(new Graph(x, y + 1, g.L, g.R + 1));
						++cnt;
					}
				}
			}
		}
	}
}

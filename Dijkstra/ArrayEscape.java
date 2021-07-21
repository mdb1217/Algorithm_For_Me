import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ArrayEscape {
	static class Graph implements Comparable<Graph> {
		int x;
		int y;
		int money;
		
		Graph(int a, int b, int d) {
			x = a;
			y = b;
			money = d;
		}

		@Override
		public int compareTo(Graph target) {
			return money - target.money;
		}
	}
	
	static int map[][];
	static boolean Visited[][];
	static int n;
	public static void main(String[] args) throws IOException {
		// 다익스트라
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		Visited = new boolean[n][n];
		
		for(int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; ++j)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		bfs();
	}

	static void bfs() {
		Graph g;
		int temp_x, temp_y;
		int diff;
		int op_x[] = {0, 1};
		int op_y[] = {1, 0};
		PriorityQueue<Graph> queue = new PriorityQueue<>();
		
		queue.add(new Graph(0, 0, 0));
		
		while(!queue.isEmpty()) {
			g = queue.poll();
			if(Visited[g.x][g.y])
				continue;
			Visited[g.x][g.y] = true;
			
			if(g.x == n - 1 && g.y == n - 1) {
				System.out.println(g.money);
				break;
			}
			
			for(int i = 0; i < 2; ++i) {
				temp_x = g.x + op_x[i];
				temp_y = g.y + op_y[i];
				
				if(temp_x < n && temp_y < n) {
					if(map[temp_x][temp_y] >= map[g.x][g.y]) {
						diff = map[temp_x][temp_y] - map[g.x][g.y] + 1;
						queue.add(new Graph(temp_x, temp_y, g.money + diff));
					}
					else
						queue.add(new Graph(temp_x, temp_y, g.money));
				}
			}
		}
	}
}

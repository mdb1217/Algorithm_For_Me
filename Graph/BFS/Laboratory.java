import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Laboratory {
	static class Graph {
		int x;
		int y;
		
		Graph(int a, int b) {
			x = a;
			y = b;
		}
	}
	
	static ArrayList<Graph> list = new ArrayList<>();
	static int min = 100;
	static int N;
	static int M;
	static int op_X[] = {0,0,1,-1};// µ¿¼­³²ºÏ xÁÂÇ¥
	static int op_Y[] = {1,-1,0,0};// µ¿¼­³²ºÏ yÁÂÇ¥
	static int map[][];
	static boolean visited[][];
	public static void main(String[] args) throws IOException {
		// ¸Õ°¡ bfsÀÇ ´À³¦ÀÌ¿Â´Ù..
		int val;
		int area;
		StringTokenizer st;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		area = N * M;
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				val = Integer.parseInt(st.nextToken());
				map[i][j] = val;
				if(val == 2) {
					list.add(new Graph(i, j));
					area = area - 1;
				}
				else if(val == 1)
					area = area - 1;		
			}
		}
		dfs(0, 0, 0);
		System.out.println(area - min - 3);

	}
	
	static void dfs(int x, int y, int depth) {
		int empty_x;
		int empty_y;
		
		if(depth == 3) {
			visited = new boolean[N][M];
			min = Math.min(bfs(), min);
		}
		else {
			for(int i = x * M + y; i < N * M; i++) {
				empty_x = i / M;
				empty_y = i % M;
				if(map[empty_x][empty_y] == 0) {
					map[empty_x][empty_y] = 1;
					dfs(empty_x, empty_y + 1, depth + 1);
					map[empty_x][empty_y] = 0;
				}
			}
		}
	}
	
	static int bfs() {
		Graph g;
		int cnt = 0;
		int x, y;
		int op_x, op_y;
		Queue<Graph> queue = new LinkedList<>();
		queue.addAll(list);
		
		while(!queue.isEmpty()) {
			g = queue.poll();
			x = g.x;
			y = g.y;
			for(int i = 0; i < 4; i++) {
				op_x = x + op_X[i];
				op_y = y + op_Y[i];
				if(op_x >= 0 && op_x < N && op_y >= 0 && op_y < M) {
					if(map[op_x][op_y] == 0 && !visited[op_x][op_y]) {
						visited[op_x][op_y] = true;
						++cnt;
						queue.add(new Graph(op_x, op_y));
					}
				}
			}
		}
		return cnt;
	}

}

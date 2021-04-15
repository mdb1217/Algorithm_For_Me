import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Key {
	static class Graph {
		int x, y;
		
		Graph(int a, int b) {
			x = a;
			y = b;
		}
	}
	
	static class LockGraph {
		int x, y;
		char lock;
		
		LockGraph(int a, int b, char c) {
			x = a;
			y = b;
			lock = c;
		}
	}
	
	static int h, w;
	static Queue<LockGraph> lock_queue = new LinkedList<>();
	static Queue<Graph> queue = new LinkedList<>();
	static boolean key_list[] = new boolean[26];
	static int cnt = 0;
	static char Map[][];
	static boolean Visited[][];
	public static void main(String[] args) throws IOException {
		// bfs
		StringBuilder sb = new StringBuilder();
		int T;
		StringTokenizer st;
		String s;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		
		for(int k = 0; k < T; ++k) {
			lock_queue = new LinkedList<>();
			queue = new LinkedList<>();
			key_list = new boolean[26];
			cnt = 0;
			
			st = new StringTokenizer(br.readLine());

			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			Map = new char[h][w];
			Visited = new boolean[h][w];
			for(int i = 0; i < h; ++i) {
				s = br.readLine();
				for(int j = 0; j < w; ++j) {
					Map[i][j] = s.charAt(j);
					if(i == 0 || j == 0 || i == h - 1 || j == w - 1) {
						if(Map[i][j] != '*') {
							if(Map[i][j] == '.') {
								queue.add(new Graph(i, j));
								Visited[i][j] = true;
							}
							else if(Map[i][j] == '$') {
								++cnt;
								queue.add(new Graph(i, j));
								Visited[i][j] = true;
							}
							else if(Map[i][j] <= 90) {
								lock_queue.add(new LockGraph(i, j, Map[i][j]));
								Visited[i][j] = true;
							}
							else {
								key_list[Map[i][j] - 97] = true;
								queue.add(new Graph(i, j));
								Visited[i][j] = true;
							}
						}
					}
				}
			}
			
			s = br.readLine();
			if(s.charAt(0) != '0') {
				for(int i = 0; i < s.length(); ++i) {
					key_list[s.charAt(i) - 97] = true;
					lock_bfs(s.charAt(i));
				}
			}
			
			bfs();
			
			sb.append(cnt + "\n");
		}
		System.out.println(sb.toString());
	}
	
	static void bfs() {
		Graph g;
		int temp_x, temp_y;
		int op_x[] = {1, -1, 0, 0};
		int op_y[] = {0, 0, 1, -1};
		
		while(!queue.isEmpty()) {
			g = queue.poll();
			
			for(int i = 0; i < 4; ++i) {
				temp_x = g.x + op_x[i];
				temp_y = g.y + op_y[i];
				
				if(temp_x >= 0 && temp_x < h && temp_y >= 0 && temp_y < w) {
					if(Map[temp_x][temp_y] != '*' && !Visited[temp_x][temp_y]) {
						if(Map[temp_x][temp_y] == '.') {
							Visited[temp_x][temp_y] = true;
							queue.add(new Graph(temp_x, temp_y));
						}
						else if(Map[temp_x][temp_y] == '$') {
							++cnt;
							Visited[temp_x][temp_y] = true;
							queue.add(new Graph(temp_x, temp_y));
						}
						else if(Map[temp_x][temp_y] <= 90){
							if(key_list[Map[temp_x][temp_y] - 65])
								queue.add(new Graph(temp_x, temp_y));
							else 
								lock_queue.add(new LockGraph(temp_x, temp_y, Map[temp_x][temp_y]));
							Visited[temp_x][temp_y] = true;
						}
						else {
							if(!key_list[Map[temp_x][temp_y] - 97]) {
								key_list[Map[temp_x][temp_y] - 97] = true;
								lock_bfs(Map[temp_x][temp_y]);
							}
							queue.add(new Graph(temp_x, temp_y));
							Visited[temp_x][temp_y] = true;
						}
					}
				}
			}
		}
	}

	static void lock_bfs(char key) {
		int limit = lock_queue.size();
		LockGraph lg;
		
		for(int i = 0; i < limit; ++i) {
			lg = lock_queue.poll();
			
			if(lg.lock + 32 == key)
				queue.add(new Graph(lg.x, lg.y));
			else
				lock_queue.add(lg);
		}
	}
}

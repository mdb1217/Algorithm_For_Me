import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Fire2 {
	static class Graph {
		int x;
		int y;
		
		Graph(int a, int b) {
			x = a;
			y = b;
		}
	}

	static Queue<Graph> J = new LinkedList<>();
	static Queue<Graph> Fire = new LinkedList<>();
	static int R, C;
	static boolean success = false;
	static boolean Visited[][];
	static char Map[][];
	static int op_x[] = {0,0,1,-1};
	static int op_y[] = {1,-1,0,0};
	public static void main(String[] args) throws IOException {
		// BFS
		int cnt = 0;
		String s;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		Map = new char[R][C];
		Visited = new boolean[R][C];
		for(int i = 0; i < R; ++i) {
			s = br.readLine();
			for(int j = 0; j < C; ++j) {
				Map[i][j] = s.charAt(j);
				
				if(Map[i][j] == 'J') {
					J.add(new Graph(i, j));
					Visited[i][j] = true;
				}
				else if(Map[i][j] == 'F')
					Fire.add(new Graph(i, j));
			}
		}
		
		while(!J.isEmpty()) {
			++cnt;
			FireBfs();
			JBfs();
			if(success) 
				break;
		}
		
		System.out.println(success? cnt : "IMPOSSIBLE");
	}

	static void FireBfs() {
		Graph g;
		int limit = Fire.size();
		int op_X, op_Y;
		
		for(int i = 0; i < limit; i++) {
			g = Fire.poll();
			for(int j = 0; j < 4; j++) {
				op_X = op_x[j] + g.x; 
				op_Y = op_y[j] + g.y;
				if(op_X >= 0 && op_X < R && op_Y >= 0 && op_Y < C) {
					if(Map[op_X][op_Y] != '#' && Map[op_X][op_Y] != 'F') {
						Fire.add(new Graph(op_X, op_Y));
						Map[op_X][op_Y] = 'F';
					}
				}
			}
		}
	}
	
	static void JBfs() {
		Graph g;
		int x = 0, y = 0;
		int limit = J.size();
		int op_X, op_Y;
		
		for(int i = 0; i < limit; i++) {
			g = J.poll();
			x = g.x;
			y = g.y;
			for(int j = 0; j < 4; j++) {
				op_X = op_x[j] + x; 
				op_Y = op_y[j] + y;
				if(op_X >= 0 && op_X < R && op_Y >= 0 && op_Y < C) {
					if(Map[op_X][op_Y] != '#' && Map[op_X][op_Y] != 'F' && !Visited[op_X][op_Y]) {
						Visited[op_X][op_Y] = true;
						J.add(new Graph(op_X, op_Y));
					}
				}
				else {
					success = true;
					break;
				}
			}
			if(success)
				break;
		}
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Fire {
	static class Graph {
		int x;
		int y;
		
		Graph(int a, int b) {
			x = a;
			y = b;
		}
	}
	
	static int w, h;
	static boolean success;
	static Queue<Graph> Sang = new LinkedList<>();
	static Queue<Graph> Fire = new LinkedList<>();
	static boolean Visited[][];//방문 여부
	static char Map[][];
	static int op_x[] = {0,0,1,-1};//동서남북
	static int op_y[] = {1,-1,0,0};//동서남북
	public static void main(String[] args) throws IOException {
		// 1. 불먼저 bfs
		// 2. 상근이 bfs
		// 1-2 반복
		int time;
		String s;
		int t;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++) {
			time = 0;
			success = false;
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			Map = new char[h][w];
			Visited = new boolean[h][w];
			for(int j = 0; j < h; j++) {
				s = br.readLine();
				for(int k = 0; k < w; k++) {
					Map[j][k] = s.charAt(k);
					if(Map[j][k] == '*') 
						Fire.add(new Graph(j, k));
					else if(Map[j][k] == '@')
						Sang.add(new Graph(j, k));
				}
			}
			
			while(Sang.size() > 0) {
				++time;
				FireBfs();
				SangBfs();
				if(success) {
					System.out.println(time);
					break;
				}
			}
			
			if(!success)
				System.out.println("IMPOSSIBLE");
			
			Sang.clear();
			Fire.clear();
		}
	}

	static void FireBfs() {
		Graph g;
		int x = 0, y = 0;
		int limit = Fire.size();
		int op_X, op_Y;
		
		for(int i = 0; i < limit; i++) {
			g = Fire.poll();
			x = g.x;
			y = g.y;
			for(int j = 0; j < 4; j++) {
				op_X = op_x[j] + x; 
				op_Y = op_y[j] + y;
				if(op_X >= 0 && op_X < h && op_Y >= 0 && op_Y < w) {
					if(Map[op_X][op_Y] != '#' && Map[op_X][op_Y] != '*') {
						Fire.add(new Graph(op_X, op_Y));
						Map[op_X][op_Y] = '*';
					}
				}
			}
		}
	}
	
	static void SangBfs() {
		Graph g;
		int x = 0, y = 0;
		int limit = Sang.size();
		int op_X, op_Y;
		
		for(int i = 0; i < limit; i++) {
			g = Sang.poll();
			x = g.x;
			y = g.y;
			for(int j = 0; j < 4; j++) {
				op_X = op_x[j] + x; 
				op_Y = op_y[j] + y;
				if(op_X >= 0 && op_X < h && op_Y >= 0 && op_Y < w) {
					if(Map[op_X][op_Y] != '*' && Map[op_X][op_Y] != '#' && !Visited[op_X][op_Y]) {
						Visited[op_X][op_Y] = true;
						Sang.add(new Graph(op_X, op_Y));
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

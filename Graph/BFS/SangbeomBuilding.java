import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class SangbeomBuilding {
	static class Graph {
		int x;
		int y;
		int z;
		int dis;//°Å¸®
		
		Graph(int a, int b, int c, int d) {
			x = a;
			y = b;
			z = c;
			dis = d;
		}
	}
	
	static boolean success;
	static int L, R, C;
	static char Map[][][];//ÃþÇà¿­
	static int Visited[][][];
	static int op_x[] = {0,0,1,-1};//µ¿¼­³²ºÏ
	static int op_y[] = {1,-1,0,0};//µ¿¼­³²ºÏ
	static int op_z[] = {1, -1};//»óÇÏ
	public static void main(String[] args) throws IOException {
		// bfs
		int start_x = 0; 
		int start_y = 0; 
		int start_z = 0;
		String s;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
		
			if(L == 0 && R == 0 && C == 0)
				break;
			
			Map = new char[L][R][C];
			Visited = new int[L][R][C];
			success = false;
			for(int i = 0; i < L; i++) {
				for(int j = 0; j < R; j++) {
					s = br.readLine();
					for(int k = 0; k < C; k++) {
						Map[i][j][k] = s.charAt(k);
						if(s.charAt(k) == 'S') {
							start_z = i;
							start_x = j;
							start_y = k;
						}		
					}
				}
				br.readLine();
			}
			bfs(start_x, start_y, start_z);
			if(!success)
				System.out.println("Trapped!");
		}
	}

	static void bfs(int start_x, int start_y, int start_z) {
		Graph g;
		int x = start_x, y = start_y, z = start_z;
		int cnt = 0;
		int op_X, op_Y, op_Z;
		Queue<Graph> queue = new LinkedList<>(); 
		queue.add(new Graph(x, y, z, cnt));
		Visited[z][x][y] = 1;
		
		while(!queue.isEmpty()) {
			g = queue.poll();
			cnt = g.dis + 1;
			x = g.x;
			y = g.y;
			z = g.z;
			for(int i = 0; i < 4; i++) {//µ¿¼­³²ºÏ
				op_X = op_x[i] + x; 
				op_Y = op_y[i] + y;
				if(op_X >= 0 && op_X < R && op_Y >= 0 && op_Y < C) {
					if(Map[z][op_X][op_Y] != '#' && Visited[z][op_X][op_Y] == 0) {
						if(Map[z][op_X][op_Y] == 'E') {
							success = true;
							System.out.println("Escaped in " + cnt + " minute(s).");
							break;
						}
						queue.add(new Graph(op_X, op_Y, z, cnt));
						Visited[z][op_X][op_Y] = 1;
					}
				}
			}
			
			if(success)
				break;
			
			for(int i = 0; i < 2; i++) {
				op_Z = op_z[i] + z; 
				if(op_Z >= 0 && op_Z < L) {
					if(Map[op_Z][x][y] != '#' && Visited[op_Z][x][y] == 0) {
						if(Map[op_Z][x][y] == 'E') {
							success = true;
							System.out.println("Escaped in " + cnt + " minute(s).");
							break;
						}
						queue.add(new Graph(x, y, op_Z, cnt));
						Visited[op_Z][x][y] = 1;
					}
				}
			}
			
			if(success)
				break;
		}
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BoyJump {
	static class Graph {
		int x;
		int y;
		
		Graph(int a, int b) {
			x = a;
			y = b;
		}
	}
	
	static Queue<Graph> Neoksal = new LinkedList<>();
	static Queue<Graph> Swings = new LinkedList<>();
	static Queue<Graph> Changmo = new LinkedList<>();
	static boolean N_end = false;
	static boolean S_end = false;
	static boolean C_end = false;
	static boolean success = false;
	static int R, C;
	static int count;
	static int s_count = 0;
	static String Map[];
	static boolean Visited_N[][];
	static boolean Visited_S[][];
	static boolean Visited_C[][];
	static int min_minute[][];
	static int op_x[] = {0,0,1,-1};//동서남북
	static int op_y[] = {1,-1,0,0};//동서남북
	public static void main(String[] args) throws IOException {
		// BFS
		// 전체 경우의 수 다찾기
		// 1분마다 세명 이동~
		int start_Nx;
		int start_Ny;
		int start_Sx;
		int start_Sy;
		int start_Cx;
		int start_Cy;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		Map = new String[R + 1];
		Visited_N = new boolean[R + 1][C + 1];
		Visited_S = new boolean[R + 1][C + 1];
		Visited_C = new boolean[R + 1][C + 1];
		min_minute = new int[R + 1][C + 1];
		for(int i = 1; i <= R; i++) {
			Map[i] = br.readLine();
		}
		st = new StringTokenizer(br.readLine());//넉살
		start_Nx = Integer.parseInt(st.nextToken());
		start_Ny = Integer.parseInt(st.nextToken());
		Neoksal.add(new Graph(start_Nx, start_Ny));
		Visited_N[start_Nx][start_Ny] = true;
		
		st = new StringTokenizer(br.readLine());//스윙스
		start_Sx = Integer.parseInt(st.nextToken());
		start_Sy = Integer.parseInt(st.nextToken());
		Swings.add(new Graph(start_Sx, start_Sy));
		Visited_S[start_Sx][start_Sy] = true;
		
		st = new StringTokenizer(br.readLine());//창모
		start_Cx = Integer.parseInt(st.nextToken());
		start_Cy = Integer.parseInt(st.nextToken());
		Changmo.add(new Graph(start_Cx, start_Cy));
		Visited_C[start_Cx][start_Cy] = true;
		
		while(!N_end || !S_end || !C_end) {
			++count;
			if(!N_end) 
				NeoksalBfs();
			
			if(!S_end) 
				SwingsBfs();
				
			if(!C_end)
				ChangmoBfs();
				
			if(success)
				break;
		}
		
		if(success) {
			System.out.println(count);
			System.out.println(s_count);
		}
		else
			System.out.println(-1);
	}
	
	static void NeoksalBfs() {
		Graph g;
		int x = 0, y = 0;
		int limit = Neoksal.size();
		int op_X, op_Y;
		
		if(limit == 0)
			N_end = true;
	
		for(int i = 0; i < limit; i++) {
			g = Neoksal.poll();
			x = g.x;
			y = g.y;
			for(int j = 0; j < 4; j++) {
				op_X = op_x[j] + x; 
				op_Y = op_y[j] + y;
				if(op_X >= 1 && op_X <= R && op_Y >= 1 && op_Y <= C) {
					if(Map[op_X].charAt(op_Y - 1) == '0' && !Visited_N[op_X][op_Y]) {
						Visited_N[op_X][op_Y] = true;
						Neoksal.add(new Graph(op_X, op_Y));
						min_minute[op_X][op_Y] = count;
						if(Visited_S[op_X][op_Y] && Visited_C[op_X][op_Y]) {
							success = true;
							++s_count;
						}
					}
				}
			}
		}
	}
	
	static void SwingsBfs() {
		Graph g;
		int x = 0, y = 0;
		int limit = Swings.size();
		int op_X, op_Y;
		
		if(limit == 0)
			S_end = true;
	
		for(int i = 0; i < limit; i++) {
			g = Swings.poll();
			x = g.x;
			y = g.y;
			for(int j = 0; j < 4; j++) {
				op_X = op_x[j] + x; 
				op_Y = op_y[j] + y;
				if(op_X >= 1 && op_X <= R && op_Y >= 1 && op_Y <= C) {
					if(Map[op_X].charAt(op_Y - 1) == '0' && !Visited_S[op_X][op_Y]) {
						Visited_S[op_X][op_Y] = true;
						Swings.add(new Graph(op_X, op_Y));
						min_minute[op_X][op_Y] = count;
						if(Visited_N[op_X][op_Y] && Visited_C[op_X][op_Y]) {
							success = true;
							++s_count;
						}
					}
				}
			}
		}
	}

	static void ChangmoBfs() {
		Graph g;
		int x = 0, y = 0;
		int limit = Changmo.size();
		int op_X, op_Y;
		
		if(limit == 0)
			C_end = true;
	
		for(int i = 0; i < limit; i++) {
			g = Changmo.poll();
			x = g.x;
			y = g.y;
			for(int j = 0; j < 4; j++) {
				op_X = op_x[j] + x; 
				op_Y = op_y[j] + y;
				if(op_X >= 1 && op_X <= R && op_Y >= 1 && op_Y <= C) {
					if(Map[op_X].charAt(op_Y - 1) == '0' && !Visited_C[op_X][op_Y]) {
						Visited_C[op_X][op_Y] = true;
						Changmo.add(new Graph(op_X, op_Y));
						min_minute[op_X][op_Y] = count;
						if(Visited_N[op_X][op_Y] && Visited_S[op_X][op_Y]) {
							success = true;
							++s_count;
						}
					}
				}
			}
		}
	}
}

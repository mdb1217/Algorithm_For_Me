import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Escape {
	static class Graph {
		int x;
		int y;
		
		Graph(int a, int b) {
			x = a;
			y = b;
		}
	}
	
	static boolean end = false;
	static boolean success = false;
	static int R, C;
	static ArrayList<Graph> animal = new ArrayList<>();
	static ArrayList<Graph> WaterList = new ArrayList<>();
	static char[][] Map;
	static int Visited[][];//방문 여부
	static int op_x[] = {0,0,1,-1};//동서남북
	static int op_y[] = {1,-1,0,0};//동서남북
	public static void main(String[] args) throws IOException {
		// 고슴도치 이동 : Bfs 구현
		// 물 이동 : Bfs 구현
		// 물이 1분뒤에 확장될 곳에는 고슴도치가 못감.
		int cnt = 0;
		char a;
		String s;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		Map = new char[R][C];
		Visited = new int[R][C];
		for(int i = 0; i < R; i++) {
			s = br.readLine();
			for(int j = 0; j < C; j++) {
				a = s.charAt(j);
				Map[i][j] = a;
				if(a == 'S') {
					animal.add(new Graph(i, j));
				}
				else if(a == '*') {
					WaterList.add(new Graph(i, j));
				}
			}
		}
		
		while(!end) {
			waterBfs();
			hedgehogBfs();
			++cnt;
		}
		
		if(success) {
			System.out.println(cnt);
		}
		else
			System.out.println("KAKTUS");
	}
	
	static void waterBfs() {
		int x = 0, y = 0;
		int limit = WaterList.size();
		int op_X, op_Y;
		
		for(int i = 0; i < limit; i++) {
			x = WaterList.get(0).x;
			y = WaterList.get(0).y;
			WaterList.remove(0);
			for(int j = 0; j < 4; j++) {
				op_X = op_x[j] + x; 
				op_Y = op_y[j] + y;
				if(op_X >= 0 && op_X < R && op_Y >= 0 && op_Y < C) {
					if(Map[op_X][op_Y] != '*' && Map[op_X][op_Y] != 'X' && Map[op_X][op_Y] != 'D') {
						WaterList.add(new Graph(op_X, op_Y));
						Map[op_X][op_Y] = '*';
					}
				}
			}
		}
	}
	
	static void hedgehogBfs() {
		int x = 0, y = 0;
		int limit = animal.size();
		int op_X, op_Y;
		
		if(limit == 0)
			end = true;
		
		for(int i = 0; i < limit; i++) {
			x = animal.get(0).x;
			y = animal.get(0).y;
			animal.remove(0);
			for(int j = 0; j < 4; j++) {
				op_X = op_x[j] + x; 
				op_Y = op_y[j] + y;
				if(op_X >= 0 && op_X < R && op_Y >= 0 && op_Y < C) {
					if(Map[op_X][op_Y] != '*' && Map[op_X][op_Y] != 'X' && Visited[op_X][op_Y] == 0) {
						Visited[op_X][op_Y] = 1;
						animal.add(new Graph(op_X, op_Y));
						if(Map[op_X][op_Y] == 'D') {
							end = true;
							success = true;
							break;
						}
					}
				}
			}
			if(end)
				break;
		}
	}

}

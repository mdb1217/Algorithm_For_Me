import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SandCastle {
	static class Graph {
		int x;
		int y;
		
		Graph(int a, int b) {
			x = a;
			y = b;
		}
	}
	
	static boolean end = false;
	static Queue<Graph> queue = new LinkedList<>();
	static int H, W;
	static int count[][];
	static char Map[][];
	static int op_X[] = {0,0,1,-1,1,1,-1,-1};//동서남북대각선
	static int op_Y[] = {1,-1,0,0,1,-1,1,-1};//동서남북대각선
	public static void main(String[] args) throws IOException {
		// bfs
		int time = 0;
		String s;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		Map = new char[H][W];
		count = new int[H][W];
		
		for(int i = 0; i < H; i++) {
			s = br.readLine();
			for(int j = 0; j < W; j++) {
				Map[i][j] = s.charAt(j);
				if(Map[i][j] == '.')
					queue.add(new Graph(i, j));
			}
		}
		
		while(true) {
			bfs();
			if(end)
				break;
			++time;
		}
		
		System.out.println(time);
	}
	
	static void bfs() {
		Graph g;
		int limit = queue.size();
		int x, y;
		int op_x, op_y;
		
		end = true;
		
		for(int i = 0; i < limit; i++) {
			g = queue.poll();
			x = g.x;
			y = g.y;
			
			for(int j = 0; j < 8; j++) {
				op_x = x + op_X[j];
				op_y = y + op_Y[j];
				if(op_x >= 0 && op_y >= 0 && op_x < H && op_y < W && Map[op_x][op_y] != '.') {
					++count[op_x][op_y];
					if(count[op_x][op_y] >= Map[op_x][op_y] - '0') {
						queue.add(new Graph(op_x, op_y));
						Map[op_x][op_y] = '.';
						end = false;
					}
				}
			}
		}
	}
}

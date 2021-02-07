import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class WoodPecker {
	static class Graph {
		int x;
		int y;
		int distance;
		
		Graph(int a, int b, int c) {
			x = a;
			y = b;
			distance = c;
		}
	}
	static boolean success = false;
	static int n, m;
	static String Map[];
	static int Visited[][];
	static int op_x[] = {0,0,1,-1};//µ¿¼­³²ºÏ
	static int op_y[] = {1,-1,0,0};//µ¿¼­³²ºÏ
	public static void main(String[] args) throws IOException {
		// bfs
		// 0 : ºó Ä­, 1 : Àå¾Ö¹°, 2 : ½Ä±¸, 3 : Ã»±¹Àå, 4 : ½º½Ã, 5 : ¸Æ¾ØÄ¡Áî
		int x = 0;
		int y = 0;
		int ans;
		String s;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		Map = new String[n];
		Visited = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			s = br.readLine();
			Map[i] = s;
			if(s.indexOf("2") >= 0) {
				x = i;
				y = s.indexOf("2");
			}
		}
		ans = bfs(x, y);
		if(success) {
			System.out.println("TAK");
			System.out.println(ans);
		}
		else {
			System.out.println("NIE");
		}
	}

	static int bfs(int start_x, int start_y) {
		char comp;
		Graph g;
		int x = start_x, y = start_y;
		int cnt = 0;
		int op_X, op_Y;
		Queue<Graph> queue = new LinkedList<>(); 
		queue.add(new Graph(x, y, cnt));
		Visited[x][y] = 1;
		
		while(!queue.isEmpty()) {
			g = queue.poll();
			cnt = g.distance + 1;
			x = g.x;
			y = g.y;
			for(int i = 0; i < 4; i++) {
				op_X = op_x[i] + x; 
				op_Y = op_y[i] + y;
				if(op_X >= 0 && op_X < n && op_Y >= 0 && op_Y < m) {
					comp = Map[op_X].charAt(op_Y);
					if(comp != '1' && Visited[op_X][op_Y] == 0) {
						if(comp == '3' || comp == '4' || comp == '5') {
							success = true;
							break;
						}
						queue.add(new Graph(op_X, op_Y, cnt));
						Visited[op_X][op_Y] = 1;
					}
				}
			}
			if(success)
				break;
		}
		return cnt;
	}
}

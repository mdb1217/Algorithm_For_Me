import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SevenPrincess {
	static class Graph {
		int x;
		int y;
		
		Graph(int a, int b) {
			x = a;
			y = b;
		}
	}

	static int op_x[] = {-1, 1, 0, 0};
	static int op_y[] = {0, 0, -1, 1}; 
	static int cnt = 0;
	static boolean visit[][];
	static char Map[];
	public static void main(String[] args) throws IOException {
		// 백트래킹
		// S 이다솜, Y 임도연
		String s;
		char c;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map = new char[25];
		visit = new boolean[5][5];
		
		for(int i = 0; i < 5; i++) {
			s = br.readLine();
			for(int j = 0; j < 5; j++) {
				c = s.charAt(j);
				Map[i * 5 + j] = c;
			}
		}
		
		tracking(0, 0, 0, 0);
		System.out.println(cnt);
	}
	
	static void tracking(int start, int d, int y_cnt, int index) {
		if(d == 7) {
			if(bfs(index / 5, index % 5) == 7)
				++cnt;
		}
		else {
			int temp_y = y_cnt;
			for(int i = start; i < 25; ++i) {
				visit[i / 5][i % 5] = true;
				if(Map[i] == 'Y') {
					++temp_y;
					if(temp_y < 4)
						tracking(i + 1, d + 1, temp_y, i);
					--temp_y;
				}
				else
					tracking(i + 1, d + 1, temp_y, i);
				visit[i / 5][i % 5] = false;
			}
		}
	}
	
	static int bfs(int x, int y) {
		Graph g;
		int cnt = 0;
		boolean complete[][] = new boolean[5][5];
		int temp_x, temp_y;
		Queue<Graph> queue = new LinkedList<>();
		queue.add(new Graph(x, y));
		complete[x][y] = true;
		
		while(!queue.isEmpty()) {
			g = queue.poll();
			++cnt;
			
			for(int i = 0; i < 4; ++i) {
				temp_x = g.x + op_x[i];
				temp_y = g.y + op_y[i];
				
				if(temp_x >= 0 && temp_x < 5 && temp_y >= 0 && temp_y < 5) {
					if(visit[temp_x][temp_y] && !complete[temp_x][temp_y]) {
						complete[temp_x][temp_y] = true;
						queue.add(new Graph(temp_x, temp_y));
					}
				}
			}
		}
		
		return cnt;
	}

}

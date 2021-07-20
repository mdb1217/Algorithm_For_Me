import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PopulationMove {
	static class Node {
		int x;
		int y;
		
		Node(int a, int b) {
			x = a;
			y = b;
		}
	}

	static int op_X[] = {0,0,1,-1};
	static int op_Y[] = {1,-1,0,0};
	static int N, L, R;
	static int Map[][];
	static boolean Visited[][];
	public static void main(String[] args) throws IOException {
		// bfs
		int cnt = 0;
		boolean move;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		Map = new int[N][N];
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; ++j)
				Map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		while(true) {
			move = false;
			Visited = new boolean[N][N];
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					if(!Visited[i][j]) {
						Visited[i][j] = true;
						if(bfs(i, j))
							move = true;
					}
				}
			}
			if(!move)
				break;
			++cnt;
		}
		
		System.out.println(cnt);
	}

	static boolean bfs(int x, int y) {
		Node n;
		int diff;
		int op_x, op_y;
		int sum = 0;
		int total;
		int avg;
		Queue<Node> queue = new LinkedList<>();
		Queue<Node> AVG = new LinkedList<>();
		queue.add(new Node(x, y));
		AVG.add(new Node(x, y));
		sum += Map[x][y];
		
		while(!queue.isEmpty()) {
			n = queue.poll();
			for(int i = 0; i < 4; ++i) {
				op_x = n.x + op_X[i];
				op_y = n.y + op_Y[i];
				
				if(op_x >= 0 && op_y >= 0 && op_x < N && op_y < N && !Visited[op_x][op_y]) {
					diff = Math.abs(Map[op_x][op_y] - Map[n.x][n.y]);
					if(diff >= L && diff <= R) {
						Visited[op_x][op_y] = true;
						queue.add(new Node(op_x, op_y));
						AVG.add(new Node(op_x, op_y));
						sum += Map[op_x][op_y];
					}
				}
			}
		}
		
		total = AVG.size();
		avg = sum / total;
		if(total == 1)
			return false;
		else {
			while(!AVG.isEmpty()) {
				n = AVG.poll();
				
				Map[n.x][n.y] = avg;
			}
			return true;
		}
	}
}

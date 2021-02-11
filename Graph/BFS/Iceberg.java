import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Iceberg {
	static class Graph {
		int x;
		int y;
		
		Graph(int a, int b){
			x = a;
			y = b;
		}
		
	}
	
	static Queue<Graph> queue = new LinkedList<>();
	static int cnt;//빙산 영역 개수
	static boolean divide = false;
	static int N;
	static int M;
	static int op_X[] = {0,0,1,-1};//동서남북
	static int op_Y[] = {1,-1,0,0};
	static int Map[][];
	static int Teritory[][];
	static int Visited[][] = new int[N][M];
	public static void main(String[] args) throws IOException {
		// 1. 빙산 줄이기
		// 2. BFS(빙산인 부분 체크)
		int year = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new int[N][M];
		Teritory = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
				if(Map[i][j] > 0) {
					queue.add(new Graph(i, j));
					Teritory[i][j] = 1;
					++cnt;
				}
			}
		}
		
		while(true) {
			remove();//빙산 줄이기
			++year;
			if(cnt == 0) {
				System.out.println(0);
				break;
			}
			search();
			if(divide) {
				System.out.println(year);
				break;
			}
		}
	}

	static void remove() {
		Graph g;
		int temp_x;
		int temp_y;
		int next_x;
		int next_y;
		
		while(!queue.isEmpty()) {
			g = queue.poll();
			temp_x = g.x;
			temp_y = g.y;
			for (int i = 0; i < 4; i++) {
				next_x = op_X[i] + temp_x;
				next_y = op_Y[i] + temp_y;
				
				if(next_x >= 0 && next_x < N && next_y >= 0 && next_y < M) {
					if(Map[next_x][next_y] == 0 && Teritory[next_x][next_y] == 0) {
						if(Map[temp_x][temp_y] > 0) {
							--Map[temp_x][temp_y];
							if(Map[temp_x][temp_y] == 0) {
								--cnt;
								break;
							}
						}
					}
				}
			}
		}
	}
	
	static void search() {
		Visited = new int[N][M];
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (Map[i][j] == 0)
                	Teritory[i][j] = 0;
                else if(Visited[i][j] == 0){
                	if(cnt != bfs(i, j)) {
                		divide = true;
                		break;
                	}
                }
            }
        }
	}
	
	static int bfs(int x, int y) {
		Graph g;
		Queue<Graph> iceberg = new LinkedList<>();
		int num;
		int temp_x;
		int temp_y;
		int next_x;
		int next_y;
		
		iceberg.add(new Graph(x, y));
		queue.add(new Graph(x, y));
		Visited[x][y] = 1;
		num = 1;
		
		while(!iceberg.isEmpty()) {
			g = iceberg.poll();
			temp_x = g.x;
			temp_y = g.y;
			for (int i = 0; i < 4; i++) {
				next_x = op_X[i] + temp_x;
				next_y = op_Y[i] + temp_y;
				
				if(next_x >= 0 && next_x < N && next_y >= 0 && next_y < M) {
					if(Map[next_x][next_y] > 0 && Visited[next_x][next_y] == 0) {
						Visited[next_x][next_y] = 1;
						iceberg.add(new Graph(next_x, next_y));
						queue.add(new Graph(next_x, next_y));
						++num;
					}
				}
			}
		}
		return num;
	}
}

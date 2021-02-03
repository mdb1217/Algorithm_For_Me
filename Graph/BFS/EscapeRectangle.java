import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class EscapeRectangle {
	static class Graph {
		int x;
		int y;
		int cnt;
		
		Graph(int a, int b, int c){
			x = a;
			y = b;
			cnt = c;
		}
		
	}
	
	static int H, W;
	static int N, M;
	static boolean sw = false;//도착좌표 도달시
	static Graph start;
	static int op_X[] = {0,0,1,-1};//동서남북
	static int op_Y[] = {1,-1,0,0};//동서남북
	static int Map[][];
	static int Visited[][];
	static int end_x, end_y;
	public static void main(String[] args) throws IOException {
		// bfs문제(최소 이동)
		// 1. 직사각형 왼쪽 위가 도착했는 지 판단
		// 2. 직사각형 이동(벽 없고, 격자판 벗어나지 않는 곳으로)
		// 1~2 반복
		
		int start_x, start_y;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new int[N + 1][M + 1];
		Visited = new int[N + 1][M + 1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		start_x = Integer.parseInt(st.nextToken());
		start_y = Integer.parseInt(st.nextToken());
		end_x = Integer.parseInt(st.nextToken());
		end_y = Integer.parseInt(st.nextToken());
		
		start = new Graph(start_x, start_y, 0);
		
		Visited[start_x][start_y] = 1;
		bfs();
		
		if(!sw)
			System.out.println(-1);
	}
	
	static void bfs() {
		// visited 처리 해주시길.. 왼쪽 위 기준으로..
		int x, y;
		int cnt = 0;
		Graph next_rectangle[] = new Graph[4];//0-북서 1-북동 2-남동 3-남서
		boolean wall;
		ArrayList<Graph> list = new ArrayList<>(); 
		list.add(start);
		
		while(!list.isEmpty()) {
			cnt = list.get(0).cnt;
			x = list.get(0).x;
			y = list.get(0).y;
			if(x == end_x && y == end_y) {
				sw = true;
				System.out.println(cnt);
				break;
			}
			list.remove(0);
			for(int i = 0; i < 4; i++) {
				wall = false;
				next_rectangle[0] = new Graph(op_X[i] + x, op_Y[i] + y, cnt + 1);
				next_rectangle[1] = new Graph(op_X[i] + x, op_Y[i] + y + W - 1, cnt + 1);
				next_rectangle[2] = new Graph(op_X[i] + x + H - 1, op_Y[i] + y + W - 1, cnt + 1);
				next_rectangle[3] = new Graph(op_X[i] + x + H - 1, op_Y[i] + y, cnt + 1);
				
				if(i == 0) {
					// 동
					if(next_rectangle[1].y <= M && Visited[next_rectangle[0].x][next_rectangle[0].y] == 0) {
						for(int j = next_rectangle[1].x; j <= next_rectangle[2].x; j++) {
							if(Map[j][next_rectangle[1].y] == 1) {
								//사이에 벽있으면
								wall = true;
								break;
							}
						}
						if(!wall) {
							Visited[next_rectangle[0].x][next_rectangle[0].y] = 1;
							list.add(next_rectangle[0]);
						}
					}
				}
				else if(i == 1) {
					// 서
					if(next_rectangle[0].y > 0 && Visited[next_rectangle[0].x][next_rectangle[0].y] == 0) {
						for(int j = next_rectangle[0].x; j <= next_rectangle[3].x; j++) {
							if(Map[j][next_rectangle[0].y] == 1) {
								//사이에 벽있으면
								wall = true;
								break;
							}
						}
						if(!wall) {
							Visited[next_rectangle[0].x][next_rectangle[0].y] = 1;
							list.add(next_rectangle[0]);
						}
					}
				}
				else if(i == 2) {
					// 남
					if(next_rectangle[3].x <= N && Visited[next_rectangle[0].x][next_rectangle[0].y] == 0) {
						for(int j = next_rectangle[3].y; j <= next_rectangle[2].y; j++) {
							if(Map[next_rectangle[3].x][j] == 1) {
								//사이에 벽있으면
								wall = true;
								break;
							}
						}
						if(!wall) {
							Visited[next_rectangle[0].x][next_rectangle[0].y] = 1;
							list.add(next_rectangle[0]);
						}
					}
				}
				else {
					// 북
					if(next_rectangle[0].x > 0 && Visited[next_rectangle[0].x][next_rectangle[0].y] == 0) {
						for(int j = next_rectangle[0].y; j <= next_rectangle[1].y; j++) {
							if(Map[next_rectangle[0].x][j] == 1) {
								//사이에 벽있으면
								wall = true;
								break;
							}
						}
						if(!wall) {
							Visited[next_rectangle[0].x][next_rectangle[0].y] = 1;
							list.add(next_rectangle[0]);
						}
					}
				}
				
			}
		}
	}

}

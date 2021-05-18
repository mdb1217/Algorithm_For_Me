import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SundayMorningDate {
	static class Graph {
		int x;
		int y;
		
		Graph(int a, int b) {
			x = a;
			y = b;
		}
	}
	
	static class Trash {
		int num;
		int side;
		
		Trash(int a, int b) {
			num = a;
			side = b;
		}
	}
	
	static int op_x[] = {0, 0, 1, -1};
	static int op_y[] = {1, -1, 0, 0};
	static int N, M;
	static int side_trash[][];
	static Trash trash[][];
	static char Map[][];
	public static void main(String[] args) throws IOException {
		// 다익스트라, BFS
		int temp_x, temp_y;
		boolean side;
		Trash t = new Trash(Integer.MAX_VALUE, Integer.MAX_VALUE);
		int start_x = 0, start_y = 0;
		int end_x = 0, end_y = 0;
		String s;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		trash = new Trash[N][M];
		Map = new char[N][M];
		side_trash = new int[N][M];
		for(int i = 0; i < N; ++i) {
			s = br.readLine();
			Arrays.fill(trash[i], t);
			for(int j = 0; j < M; ++j) {
				Map[i][j] = s.charAt(j);	
				if(Map[i][j] == 'S') {
					start_x = i;
					start_y = j;
						
					trash[i][j] = new Trash(0, 0);
				}
					
				else if(Map[i][j] == 'F') {
					end_x = i;
					end_y = j;
				}
			}
		}
		
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < M; ++j) {
				if(Map[i][j] != 'g') {
					side = false;
					for(int k = 0; k < 4; ++k) {
						temp_x = i + op_x[k];
						temp_y = j + op_y[k];
						if(temp_x >= 0 && temp_x < N && temp_y >= 0 && temp_y < M) {
							if(Map[temp_x][temp_y] == 'g') {
								side = true;
								break;
							}
						}
					}
					if(side)
						side_trash[i][j] = 1;
				}
			}
		}
		
		bfs(start_x, start_y);
		
		System.out.println(trash[end_x][end_y].num + " " + trash[end_x][end_y].side);
	}

	static void bfs(int start_x, int start_y) {
		Graph g;
		int temp_x, temp_y;
		Queue<Graph> queue = new LinkedList<>();
		
		queue.add(new Graph(start_x, start_y));
		
		while(!queue.isEmpty()) {
			g = queue.poll();
			
			for(int i = 0; i < 4; ++i) {
				temp_x = g.x + op_x[i];
				temp_y = g.y + op_y[i];
				
				if(temp_x >= 0 && temp_x < N && temp_y >= 0 && temp_y < M) {
					if(Map[temp_x][temp_y] == 'g') {
						if(trash[temp_x][temp_y].num > trash[g.x][g.y].num + 1) {
							trash[temp_x][temp_y] = new Trash(trash[g.x][g.y].num + 1, trash[g.x][g.y].side);
							queue.add(new Graph(temp_x, temp_y));
						}
						else if(trash[temp_x][temp_y].num == trash[g.x][g.y].num + 1 && trash[temp_x][temp_y].side > trash[g.x][g.y].side) {
							trash[temp_x][temp_y] = new Trash(trash[g.x][g.y].num + 1, trash[g.x][g.y].side);
							queue.add(new Graph(temp_x, temp_y));
						}
					}
					else if(Map[temp_x][temp_y] == 'F') {
						if(trash[temp_x][temp_y].num > trash[g.x][g.y].num)
							trash[temp_x][temp_y] = new Trash(trash[g.x][g.y].num, trash[g.x][g.y].side);
						else if(trash[temp_x][temp_y].num == trash[g.x][g.y].num && trash[temp_x][temp_y].side > trash[g.x][g.y].side)
							trash[temp_x][temp_y] = new Trash(trash[g.x][g.y].num, trash[g.x][g.y].side);
					}
					else {
						if(trash[temp_x][temp_y].num > trash[g.x][g.y].num) {
							trash[temp_x][temp_y] = new Trash(trash[g.x][g.y].num, trash[g.x][g.y].side + side_trash[temp_x][temp_y]);
							queue.add(new Graph(temp_x, temp_y));
						}
						else if(trash[temp_x][temp_y].num == trash[g.x][g.y].num && trash[temp_x][temp_y].side > trash[g.x][g.y].side + side_trash[temp_x][temp_y]) {
							trash[temp_x][temp_y] = new Trash(trash[g.x][g.y].num, trash[g.x][g.y].side + side_trash[temp_x][temp_y]);
							queue.add(new Graph(temp_x, temp_y));
						}
					}
				}
			}
		}
	}
}

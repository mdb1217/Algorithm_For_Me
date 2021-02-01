import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Mineral {
	
	static class graph {
		int x;
		int y;
		
		graph(int a, int b){
			x = a;
			y = b;
		}
		
	}
	
	static boolean south, right, left;
	static int R, C;
	static boolean more;
	static int op_X[] = {0,0,-1,1};//동서남북
	static int op_Y[] = {1,-1,0,0};//동서남북
	static int [] visited_line;
	static int [] line_max_height;
	static int[][] Visited;
	static char[][] Map;
	public static void main(String[] args) throws IOException {
		// 구현 문제(노가다..)
		// 왼오왼오 반복해서 파괴
		// 파괴 -> 위나 양옆, 아래에 미네랄이 있을 때는 bfs로 공중에 떠 있는지 탐색 ->떨어짐(가능할때만) ->  ... 반복해서 코드 짜면 될듯
		int a;
		String s;
		int N;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		Map = new char[R + 1][C + 1];
		for(int i = R; i >= 1; i--) {
			s = br.readLine();
			for(int j = 0; j < C; j++) {
				Map[i][j + 1] = s.charAt(j);
			}
		}
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			south = true;
			right = true;
			left = true;
			more = true;
			a = Integer.parseInt(st.nextToken());
			if(i % 2 == 0) {
				for(int j = 1; j <= C; j++) {
					if(Map[a][j] == 'x') {
						Map[a][j] = '.';
						if(a + 1 <= R && Map[a + 1][j] == 'x') {
							Visited = new int[R + 1][C + 1];
							visited_line = new int[C + 1];
							line_max_height = new int[C + 1];
							more = false;
							clusterBfs(a + 1, j);
						}
						if(more && right && j + 1 <= C && Map[a][j + 1] == 'x') {
							Visited = new int[R + 1][C + 1];
							visited_line = new int[C + 1];
							line_max_height = new int[C + 1];
							more = false;
							clusterBfs(a, j + 1);
						}
						if(more && south && a - 1 >= 1 && Map[a - 1][j] == 'x') {
							Visited = new int[R + 1][C + 1];
							visited_line = new int[C + 1];
							line_max_height = new int[C + 1];
							clusterBfs(a - 1, j);
						}
						break;
					}
				}
			}
			else {
				for(int j = C; j >= 1; j--) {
					if(Map[a][j] == 'x') {
						Map[a][j] = '.';
						if(a + 1 <= R && Map[a + 1][j] == 'x') {
							Visited = new int[R + 1][C + 1];
							visited_line = new int[C + 1];
							line_max_height = new int[C + 1];
							more = false;
							clusterBfs(a + 1, j);
						}
						if(more && left && j - 1 >= 1 && Map[a][j - 1] == 'x') {
							Visited = new int[R + 1][C + 1];
							visited_line = new int[C + 1];
							line_max_height = new int[C + 1];
							more = false;
							clusterBfs(a, j - 1);
						}
						if(more && south && a - 1 >= 1 && Map[a - 1][j] == 'x') {
							Visited = new int[R + 1][C + 1];
							visited_line = new int[C + 1];
							line_max_height = new int[C + 1];
							clusterBfs(a - 1, j);
						}
						break;
					}
				}
			}
		}
		
		for(int i = R; i >= 1; i--) {
			for(int j = 1; j <= C; j++) {
				System.out.print(Map[i][j]);
			}
			System.out.println();
		}
		
	}
	
	static void clusterBfs(int x, int y) {
		//바닥에 닿는 게 하나라도 있으면 괜춘
		ArrayList<graph> list = new ArrayList<>();
		boolean sw = false;
		int next_x, next_y;
		int temp_x, temp_y;
		
		Visited[x][y] = 1;
		visited_line[y] = 1;
		line_max_height[y] = x;
		list.add(new graph(x, y));
		while(!list.isEmpty()) {
			temp_x = list.get(0).x;
			temp_y = list.get(0).y;
			
			if(temp_x == x - 1 && temp_y == y)
				south = false;
			else if(temp_x == x && temp_y == y + 1)
				right = false;
			else if(temp_x == x && temp_y == y - 1)
				left = false;
			
			visited_line[temp_y] = 1;
			line_max_height[temp_y] = Math.max(temp_x, line_max_height[temp_y]);
			for (int i = 0; i < 4; i++) {
				next_x = op_X[i] + temp_x;
				next_y = op_Y[i] + temp_y;
				
				if(next_x >= 1 && next_x <= R && next_y >= 1 && next_y <= C) {
					if(Map[next_x][next_y] == 'x' && Visited[next_x][next_y] == 0) {
						Visited[next_x][next_y] = 1;
						list.add(new graph(next_x, next_y));
						if(next_x == 1) {
							sw = true;
						}
					}
				}
			}
			list.remove(0);
		}
		if(!sw) {
			move();
		}
		else
			more = true;
	}

	static void move() {
		boolean sw = false;
		while(true) {
			for (int i = 1; i <= C; i++) {
				if(visited_line[i] == 0)
					continue;
            	for (int j = 1; j <= line_max_height[i]; j++) {
                	if (Map[j][i] == 'x' && Visited[j][i] == 1) {
                    	Map[j - 1][i] = 'x';
                    	Map[j][i] = '.';
                    	Visited[j - 1][i] = 1;
                    	Visited[j][i] = 0;
                	}
            	}
            	--line_max_height[i];
        	}
		
			for (int i = 1; i <= C; i++) {
				if(visited_line[i] == 0)
					continue;
            	for (int j = 1; j <= line_max_height[i]; j++) {
                	if (Map[j][i] == 'x' && Visited[j][i] == 1) {
                    	if(j - 1 == 0) {
                    		sw = true;
                    		break;
                    	}
                    	else if(Map[j - 1][i] == 'x' && Visited[j - 1][i] == 0) {
                    		sw = true;
                    		break;
                    	}
                	}
            	}
        	}
			if(sw)
				break;
		}
	}
}

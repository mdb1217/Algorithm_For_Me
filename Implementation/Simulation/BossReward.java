import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BossReward {
	static class Graph {
		int x;
		int y;
		
		Graph(int a, int b) {
			x = a;
			y = b;
		}
	}

	static Queue<Graph> queue = new LinkedList<>();
	static int M, N, P;
	static int arrive_player = 0;
	static int dmg = 0;
	static int boss_hp;
	static String Map[];
	static HashMap<String, Integer> user = new HashMap<>();
	static boolean Visited[][];
	static int op_X[] = {1, -1, 0, 0};//동서남북
	static int op_Y[] = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		// 1. 보스에서부터 bfs돌림
		// 2. 한명이라도 도착하면 그 때부터 보스 체력 깎음.
		int start_x = 0, start_y = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		Visited = new boolean[M][N];
		Map = new String[M];
		for(int i = 0; i < M; i++) {
			Map[i] = br.readLine();
			if(Map[i].indexOf("B") >= 0) {
				start_x = i;
				start_y = Map[i].indexOf("B");
			}
		}
		
		for(int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			user.put(st.nextToken(), Integer.parseInt(st.nextToken()));
		}
		
		boss_hp = Integer.parseInt(br.readLine());
		Visited[start_x][start_y] = true;
		queue.add(new Graph(start_x, start_y));
		while(true) {
			damage();//데미지 먼저 주고 이동~
			if(boss_hp <= 0)
				break;
			bfs();
			if(arrive_player == P || queue.isEmpty())
				break;
		}
		
		System.out.println(arrive_player);
	}
	
	static void bfs() {
		Graph g;
		int x, y;
		int op_x, op_y;
		int limit = queue.size();
		for(int i = 0; i < limit; i++) {
			g = queue.poll();
			x = g.x;
			y = g.y;
			
			for(int j = 0; j < 4; j++) {
				op_x = x + op_X[j];
				op_y = y + op_Y[j];
				
				if(op_x >= 0 && op_x < M && op_y >= 0 && op_y < N) {
					if(Map[op_x].charAt(op_y) != 'X' && !Visited[op_x][op_y]) {
						Visited[op_x][op_y] = true;
						if(Map[op_x].charAt(op_y) != '.') {
							++arrive_player;
							dmg = dmg + user.get(Map[op_x].charAt(op_y) + "");
						}
						queue.add(new Graph(op_x, op_y));
					}
				}
			}
		}
	}
	
	static void damage() {
		boss_hp = boss_hp - dmg;
	}

}

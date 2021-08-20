import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class StartLink {
	static class Floor {
		int floor;
		int cnt;
		
		Floor(int a, int b) {
			floor = a;
			cnt = b;
		}
	}

	static int ans = 0;
	static boolean success = false;
	static boolean Visited[];
	static int F, S, G, U, D;
	public static void main(String[] args) throws IOException {
		// bfs
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		Visited = new boolean[F + 1];
		
		if(S == G)
			System.out.println(0);
		else {
			bfs();
			System.out.println(success? ans : "use the stairs");
		}
	}
	
	static void bfs() {
		Floor floor;
		Queue<Floor> queue = new LinkedList<>();
		Visited[S] = true;
		queue.add(new Floor(S, 0));
		
		while(!queue.isEmpty()) {
			floor = queue.poll();
			
			if(floor.floor + U <= F && !Visited[floor.floor + U]) {
				if(floor.floor + U == G) {
					success = true;
					ans = floor.cnt + 1;
					break;
				}
				Visited[floor.floor + U] = true;
				queue.add(new Floor(floor.floor + U, floor.cnt + 1));
			}
			if(floor.floor - D >= 1 && !Visited[floor.floor - D]) {
				if(floor.floor - D == G) {
					success = true;
					ans = floor.cnt + 1;
					break;
				}
				Visited[floor.floor - D] = true;
				queue.add(new Floor(floor.floor - D, floor.cnt + 1));
			}
		}
	}
	
}

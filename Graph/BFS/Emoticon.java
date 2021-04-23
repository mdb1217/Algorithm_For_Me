import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Emoticon {
	static class Emo {
		int n;
		int clipboard;
		int cnt;
		
		Emo(int a, int b, int c) {
			n = a;
			clipboard = b;
			cnt = c;
		}
	}
	
	static boolean Visited[][] = new boolean[2001][2001];
	static int goal;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// bfs
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		goal = Integer.parseInt(br.readLine());
		bfs();
	}
	
	static void bfs() {
		Emo e;
		Queue<Emo> queue = new LinkedList<>();
		queue.add(new Emo(1, 0, 0));
		
		while(!queue.isEmpty()) {
			e = queue.poll();
			
			if(e.n == goal) {
				System.out.println(e.cnt);
				break;
			}
			
			if(e.clipboard != 0) {
				if(e.n + e.clipboard <= 2000  && e.clipboard <= 1000 && !Visited[e.n + e.clipboard][e.clipboard]) {
					queue.add(new Emo(e.n + e.clipboard, e.clipboard, e.cnt + 1));
					Visited[e.n + e.clipboard][e.clipboard] = true;
				}
			}
				
			if(!Visited[e.n][e.n]) {
				queue.add(new Emo(e.n, e.n, e.cnt + 1));
				Visited[e.n][e.n] = true;
			}
			if(e.n > 1) {
				if(!Visited[e.n - 1][e.clipboard]) {
					queue.add(new Emo(e.n - 1, e.clipboard, e.cnt + 1));
					Visited[e.n - 1][e.clipboard] = true;
				}
			}
		}
	}
	
}

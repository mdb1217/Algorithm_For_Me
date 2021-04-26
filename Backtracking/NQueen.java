import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NQueen {

	static int cnt = 0;
	static int N;
	static boolean line[];
	static boolean Visited[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 백트래킹
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		Visited = new boolean[N + 1][N + 1];
		line = new boolean[N + 1];
		
		tracking(0);
		System.out.println(cnt);
	}
	
	static void tracking(int d) {
		if(d == N) {
			++cnt;
		}
		else {
			for(int i = 1; i <= N; ++i) {
				if(!line[i]) {
					boolean success = true;
					int x = d;
					int y = i - 1;
					while(y > 0 && x > 0) {
						if(Visited[x][y]) {
							success = false;
							break;
						}
						--x;
						--y;
					}
					
					if(!success)
						continue;
					
					x = d + 2;
					y = i + 1;
					while(y <= N && x <= N) {
						if(Visited[x][y]) {
							success = false;
							break;
						}
						++x;
						++y;
					}
					
					if(!success)
						continue;
					
					x = d;
					y = i + 1;
					while(y <= N && x > 0) {
						if(Visited[x][y]) {
							success = false;
							break;
						}
						--x;
						++y;
					}
					
					if(!success)
						continue;
					
					x = d + 2;
					y = i - 1;
					while(y > 0 && x <= N) {
						if(Visited[x][y]) {
							success = false;
							break;
						}
						++x;
						--y;
					}
					
					if(!success)
						continue;
					
					Visited[d + 1][i] = true;
					line[i] = true;
					tracking(d + 1);
					Visited[d + 1][i] = false;
					line[i] = false;
				}
			}
		}
	}
}

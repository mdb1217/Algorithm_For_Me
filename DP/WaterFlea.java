import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class WaterFlea {
	static class Flea {
		int h;
		int time;
		
		Flea(int a, int b) {
			time = a;
			h = b;
		}
	}

	public static void main(String[] args) throws IOException {
		// DP
		long memo[][];
		int K, N;
		long ans = 0;
		boolean Visited[][];
		Flea f;
		Queue<Flea> queue = new LinkedList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		memo = new long[N + 1][127];
		Visited = new boolean[N + 1][127];
		memo[0][K] = 1;
		
		if(K == 0)
			System.out.println(0);
		else {
			queue.add(new Flea(0, K));
			while(!queue.isEmpty()) {
				f = queue.poll();
				if(f.time >= N)
					break;
				if(f.h == 0) {
					memo[f.time + 1][0] += memo[f.time][0] * 2;
					if(!Visited[f.time + 1][0]) {
						Visited[f.time + 1][0] = true;
						queue.add(new Flea(f.time + 1, 0));
					}
				}
				else {
					memo[f.time + 1][f.h + 1] += memo[f.time][f.h];
					if(!Visited[f.time + 1][f.h + 1]) {
						Visited[f.time + 1][f.h + 1] = true;
						queue.add(new Flea(f.time + 1, f.h + 1));
					}
				
					memo[f.time + 1][f.h - 1] += memo[f.time][f.h];
					if(!Visited[f.time + 1][f.h - 1]) {
						Visited[f.time + 1][f.h - 1] = true;
						queue.add(new Flea(f.time + 1, f.h - 1));
					}
				}
			}
			
			for(int i = 1; i <= 126; i++)
				ans = ans + memo[N][i];
			System.out.println(ans);
		}
	}
}

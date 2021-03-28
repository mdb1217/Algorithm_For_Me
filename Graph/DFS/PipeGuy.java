import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PipeGuy {

	static int merge_cnt;
	static boolean merge;
	static int Visited[][];
	static char Map[][];
	public static void main(String[] args) throws IOException {
		// dfs
		// 사이클의 개수 = safe zone의 개수
		int cnt = 0;
		String s;
		int N, M;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new char[N][M];
		Visited = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			s = br.readLine();
			for(int j = 0; j < M; j++)
				Map[i][j] = s.charAt(j);
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(Visited[i][j] == 0) {
					merge = false;
					++cnt;
					cycle_dfs(i, j, cnt);
					if(merge)
						--cnt;
				}
			}
		}
		
		System.out.println(cnt);
	}

	static void cycle_dfs(int x, int y, int cnt) {
		int op_x = x, op_y = y;
		if(Visited[x][y] == 0) {
			Visited[x][y] = cnt;
			switch(Map[x][y]) {
				case 'U': op_x -= 1; 
				break;
				
				case 'D': op_x += 1;
				break;
				
				case 'L': op_y -= 1;
				break;
				
				case 'R': op_y += 1;
				break;
			}
			cycle_dfs(op_x, op_y, cnt);
			if(merge) 
				Visited[x][y] = merge_cnt;
		}
		else {
			if(Visited[x][y] != cnt) {
				merge = true;// 합쳐지는 경우
				merge_cnt = Visited[x][y];
			}
		}
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SelectionList {

	static int max;
	static boolean Visited[];
	static int list[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 백트래킹
		StringBuilder sb = new StringBuilder();
		int T;
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; ++i) {
			list = new int[11][11];
			Visited = new boolean[11];
			max = 0;
			for(int j = 0; j < 11; ++j) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < 11; ++k)
					list[j][k] = Integer.parseInt(st.nextToken());
			}
			
			tracking(0, 0);
			
			sb.append(max).append("\n");
		}
		
		System.out.print(sb);
	}

	static void tracking(int d, int sum) {
		if(d == 11)
			max = Math.max(sum, max);
		else {
			for(int i = 0; i < 11; ++i) {
				if(!Visited[i] && list[i][d] != 0) {
					Visited[i] = true;
					tracking(d + 1, sum + list[i][d]);
					Visited[i] = false;
				}
			}
		}
	}
}

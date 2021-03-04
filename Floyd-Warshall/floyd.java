import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class floyd {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 플로이드 와샬
		int cost[][];
		int n, m;
		int start, end;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		cost = new int[n + 1][n + 1];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			if(cost[start][end] == 0)
				cost[start][end] = Integer.parseInt(st.nextToken());
			else
				cost[start][end] = Math.min(Integer.parseInt(st.nextToken()), cost[start][end]);
		}
		
		for(int i = 1; i <= n; i++) {//거쳐가는 노드
			for(int j = 1; j <= n; j++) {//출발 노드
				for(int k = 1; k <= n; k++) {//도착 노드
					if(i == j || j == k || i == k || cost[j][i] == 0 || cost[i][k] == 0)
						continue;
					if(cost[j][k] == 0) 
						cost[j][k] = cost[j][i] + cost[i][k];
					else
						cost[j][k] = Math.min(cost[j][i] + cost[i][k], cost[j][k]);
				}
			}
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				System.out.print(cost[i][j] + " ");
			}
			System.out.println();
		}
	}

}

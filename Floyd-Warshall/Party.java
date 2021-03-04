import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Party {

	public static void main(String[] args) throws IOException {
		// �÷��̵� �ͼ�
		int cost[][];
		int max = 0;
		int N, M, X;
		int start, end;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());;

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		cost = new int[N + 1][N + 1];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			cost[start][end] = Integer.parseInt(st.nextToken());
		}
				
		for(int i = 1; i <= N; i++) {//���İ��� ���
			for(int j = 1; j <= N; j++) {//��� ���
				for(int k = 1; k <= N; k++) {//���� ���
					if(i == j || j == k || i == k || cost[j][i] == 0 || cost[i][k] == 0)
						continue;
					if(cost[j][k] == 0) 
						cost[j][k] = cost[j][i] + cost[i][k];
					else
						cost[j][k] = Math.min(cost[j][i] + cost[i][k], cost[j][k]);
				}
			}
		}
				
		for(int i = 1; i <= N; i++) 
			max = Math.max(max, cost[i][X] + cost[X][i]);
		System.out.println(max);
	}

}

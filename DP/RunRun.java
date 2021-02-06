import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RunRun {
	static int M;
	static int list[];
	static int memo[][];//1번째 인덱스 : 분, 2번째 인덱스 : 지침 지수 
	public static void main(String[] args) throws IOException {
		// DP
		// 지침 지수 고려(쉬면 0될때까지 쉼)
		// 마지막은 지침지수 0이어야해서 마지막 분 무조건 못 달림
		int N;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		memo = new int[N + 1][M + 1];
		list = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			list[i] = Integer.parseInt(br.readLine());
		}
		memo[1][0] = 0;
		memo[1][1] = list[1];
		
		System.out.println(DP(N, 0));
	}
	
	static int DP(int minute, int tired) {
		if(minute == 1) {//1분일때
			if(tired == 1)
				return memo[1][1];
			else if(tired == 0)
				return 0;
			else
				return 0;
		}
		if(minute < 1 || minute < tired)
			return 0;
		if(memo[minute][tired] > 0)
			return memo[minute][tired];
		if(tired == 0) {
			memo[minute][tired] = DP(minute - 1, 0);
			for(int i = 1; i <= M; i++)
				memo[minute][tired] = Math.max(memo[minute][tired], DP(minute - i, i));
		}
		else {
			memo[minute][tired] = memo[minute - 1][tired - 1] + list[minute];
		}
		return memo[minute][tired];
	}

}

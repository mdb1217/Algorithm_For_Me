import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WeakYoonho {
	
	static int N;
	static String S;
	static int memo[][];// 1번쨰 인덱스 깊이, 2번쨰는 현재 참조하는 인덱스
	static char meal[] = {'B', 'L', 'D'};
	public static void main(String[] args) throws IOException {
		// 모든 경우의 수 찾아야 할 것 같은 느낌.. dp접근
		// 실행 줄이기 위해 메모이제이션
		// B -> 아침, L -> 점심, D -> 저녁
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		memo = new int[3 * N][3 * N];
		S = br.readLine();
		System.out.println(dp(0, 0, 3 * N - 1, 0));
	}
	
	static int dp(int next_meal, int start, int end, int d) {
		boolean sw = true;//하나라도 들어갈 수 있으면 false, 아니면 true
		if(start > end)
			return 0;
		if(memo[start][end] > 0)
			return memo[start][end];
		else {
			for(int i = 0; i < 2; i++) {//앞뒤앞뒤 비교
				if(i == 0) {
					if(S.charAt(start) == meal[next_meal % 3]) {
						memo[start][end] = Math.max(memo[start][end], dp(next_meal + 1, start + 1, end, d + 1) + 1);
						sw = false;
					}
				}
				else if(i == 1) {
					if(S.charAt(end) == meal[next_meal % 3]) {
						memo[start][end] = Math.max(memo[start][end], dp(next_meal + 1, start, end - 1, d + 1) + 1);
						sw = false;
					} 
				}
			}
		}
		if(sw) {
			return 0;
		}
		return memo[start][end];
	}

}

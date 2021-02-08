import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WeakYoonho {
	
	static int N;
	static String S;
	static int memo[][];// 1���� �ε��� ����, 2������ ���� �����ϴ� �ε���
	static char meal[] = {'B', 'L', 'D'};
	public static void main(String[] args) throws IOException {
		// ��� ����� �� ã�ƾ� �� �� ���� ����.. dp����
		// ���� ���̱� ���� �޸������̼�
		// B -> ��ħ, L -> ����, D -> ����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		memo = new int[3 * N][3 * N];
		S = br.readLine();
		System.out.println(dp(0, 0, 3 * N - 1, 0));
	}
	
	static int dp(int next_meal, int start, int end, int d) {
		boolean sw = true;//�ϳ��� �� �� ������ false, �ƴϸ� true
		if(start > end)
			return 0;
		if(memo[start][end] > 0)
			return memo[start][end];
		else {
			for(int i = 0; i < 2; i++) {//�յھյ� ��
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

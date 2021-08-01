import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AllPrize {

	static int memo[][][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		// DP
		int N;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		memo = new int[N + 1][2][3];
		
		System.out.println(DP(N, 0, 0));
	}

	static int DP(int day, int lazy, int absence) {
		if(day == 0)
			return 1;
		if(memo[day][lazy][absence] > 0)
			return memo[day][lazy][absence];
		else {
			int sum = 0;
			if(lazy < 1)
				sum = DP(day - 1, lazy + 1, 0) % 1000000;
			if(absence < 2)
				sum = (sum + DP(day - 1, lazy, absence + 1)) % 1000000;
			sum = (sum + DP(day - 1, lazy, 0)) % 1000000;
			memo[day][lazy][absence] = sum;
			return memo[day][lazy][absence];
		}
	}
}
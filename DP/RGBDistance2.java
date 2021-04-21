import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGBDistance2 {
	
	static int price[][];
	static int memo[][][];
	public static void main(String[] args) throws IOException {
		// DP
		int min;
		int N;
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		memo = new int[N + 1][3][3];
		price = new int[N + 1][3];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++)
				price[i][j] = Integer.parseInt(st.nextToken());
		}
		
		min = Math.min(DP(N, 0, 1) + price[1][1], DP(N, 0, 2) + price[1][2]);
	
		min = Math.min(DP(N, 1, 0) + price[1][0], min);
		min = Math.min(min, DP(N, 1, 2) + price[1][2]);
		
		min = Math.min(DP(N, 2, 0) + price[1][0], min);
		min = Math.min(DP(N, 2, 1) + price[1][1], min);
		
		System.out.println(min);
	}

	static int DP(int house, int color, int start_color) {
		if(house == 2) {
			if(start_color != color)
				return price[house][color];
			else
				return 10000;
		}
		if(memo[house][color][start_color] > 0)
			return memo[house][color][start_color];
		else
			memo[house][color][start_color] = price[house][color] + Math.min(DP(house - 1, (color + 1) % 3, start_color), DP(house - 1, (color + 2) % 3, start_color));
		return memo[house][color][start_color];
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DDR {
	
	public static void main(String[] args) throws IOException {
		// DP
		int memo[][][];
		int min = Integer.MAX_VALUE;
		ArrayList<Integer> list = new ArrayList<>();
		int n;
		int one_sum, two_sum;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		while((n = Integer.parseInt(st.nextToken())) != 0)
			list.add(n);
		
		if(list.isEmpty())
			System.out.println(0);
		else {
			memo = new int[5][5][list.size() + 1];
			memo[list.get(0)][0][1] = 2;
			memo[0][list.get(0)][1] = 2;
			
			for(int k = 1; k < list.size(); ++k) {
				n = list.get(k);
				for(int i = 0; i < 5; ++i) {
					for(int j = 0; j < 5; ++j) {
						if(memo[i][j][k] != 0) {
							if(i == 0)
								one_sum = memo[i][j][k] + 2;
							else if(i == n)
								one_sum = memo[i][j][k] + 1;
							else if(i % 2 == n % 2) 
								one_sum = memo[i][j][k] + 4;
							else 
								one_sum = memo[i][j][k] + 3;
							
							if(j == 0)
								two_sum = memo[i][j][k] + 2;
							else if(j == n)
								two_sum = memo[i][j][k] + 1;
							else if(j % 2 == n % 2) 
								two_sum = memo[i][j][k] + 4;
							else 
								two_sum = memo[i][j][k] + 3;
							
							if(memo[n][j][k + 1] == 0)
								memo[n][j][k + 1] = one_sum;
							else
								memo[n][j][k + 1] = Math.min(one_sum, memo[n][j][k + 1]);
							
							if(memo[i][n][k + 1] == 0)
								memo[i][n][k + 1] = two_sum;
							else
								memo[i][n][k + 1] = Math.min(two_sum, memo[i][n][k + 1]);
						}
					}
				}
			}
			
			for(int i = 0; i < 5; ++i) {
				if((n = memo[list.get(list.size() - 1)][i][list.size()]) != 0)
					min = Math.min(min, n);
				
				if((n = memo[i][list.get(list.size() - 1)][list.size()]) != 0)
					min = Math.min(min, n);
			}
			
			System.out.println(min);
		}
	}

}

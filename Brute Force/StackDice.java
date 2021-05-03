import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StackDice {

	static int max = 0;
	static int list[][];
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 브루트 포스
		int temp_max;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		list = new int[N][6];
		
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < 6; ++j)
				list[i][j] = Integer.parseInt(st.nextToken());
		}
		
		temp_max = Math.max(list[0][1], list[0][2]);
		temp_max = Math.max(temp_max, list[0][3]);
		temp_max = Math.max(temp_max, list[0][4]);
		tracking(1, temp_max, list[0][5]);
		
		temp_max = Math.max(list[0][0], list[0][2]);
		temp_max = Math.max(temp_max, list[0][4]);
		temp_max = Math.max(temp_max, list[0][5]);
		tracking(1, temp_max, list[0][3]);
		
		temp_max = Math.max(list[0][0], list[0][1]);
		temp_max = Math.max(temp_max, list[0][3]);
		temp_max = Math.max(temp_max, list[0][5]);
		tracking(1, temp_max, list[0][4]);
		
		temp_max = Math.max(list[0][0], list[0][2]);
		temp_max = Math.max(temp_max, list[0][4]);
		temp_max = Math.max(temp_max, list[0][5]);
		tracking(1, temp_max, list[0][1]);
		
		temp_max = Math.max(list[0][0], list[0][1]);
		temp_max = Math.max(temp_max, list[0][3]);
		temp_max = Math.max(temp_max, list[0][5]);
		tracking(1, temp_max, list[0][2]);
		
		temp_max = Math.max(list[0][1], list[0][2]);
		temp_max = Math.max(temp_max, list[0][3]);
		temp_max = Math.max(temp_max, list[0][4]);
		tracking(1, temp_max, list[0][0]);
		
		System.out.println(max);
	}

	static void tracking(int d, int sum, int num) {
		if(d == N)
			max = Math.max(sum, max);
		else {
			int temp_max = 0;
			int temp_num = 0;
			for(int i = 0; i < 6; ++i) {
				if(num == list[d][i]) {
					if(i == 0)
						temp_num = list[d][5];
					else if(i == 1)
						temp_num = list[d][3];
					else if(i == 2)
						temp_num = list[d][4];
					else if(i == 3)
						temp_num = list[d][1];
					else if(i == 4)
						temp_num = list[d][2];
					else 
						temp_num = list[d][0];
				}
				else
					temp_max = Math.max(temp_max, list[d][i]);
			}
			
			if(temp_max == temp_num) {
				--temp_max;
				if(temp_max == num)
					--temp_max;
			}
			
			tracking(d + 1, sum + temp_max, temp_num);
		}
	}
}

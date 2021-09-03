import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Cramming {

	public static void main(String[] args) throws IOException {
		// Knapsack
		int N, T;
		int K, S;
		int max[];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		max = new int[T+1];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());
			for(int j = T; j >= K; j--)
				max[j] =  max[j] = Math.max(max[j], max[j - K] + S);
		}
	
		System.out.println(max[T]);
	}
}
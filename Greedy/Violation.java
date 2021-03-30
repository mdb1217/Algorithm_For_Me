import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Violation {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// ±×¸®µð
		int N;
		int cnt = 1;
		int score;
		long hacker = 0;
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			score = Integer.parseInt(br.readLine());
			queue.add(score);
		}
		
		while(!queue.isEmpty()) {
			score = queue.poll();
			if(score == cnt)
				++cnt;
			else if(score > cnt) {
				hacker += (score - cnt);
				++cnt;
			}
		}
		
		System.out.println(hacker);
	}

}

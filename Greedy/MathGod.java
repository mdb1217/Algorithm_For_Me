import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MathGod {
	static class Num implements Comparable<Num> {
		String s;
		
		Num(String a) {
			s = a;
		}

		@Override
		public int compareTo(Num target) {
			if(s.charAt(0) > target.s.charAt(0))
				return -1;
			else if(s.charAt(0) == target.s.charAt(0)) {
				return (target.s + s).compareTo(s + target.s);
			}
			else
				return 1;
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		// ±×¸®µð
		StringBuilder sb = new StringBuilder();
		String s;
		int K, N;
		int cnt = 0;
		int max = 0;
		
		PriorityQueue<Num> queue = new PriorityQueue<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < K; ++i) {
			s = br.readLine();
			queue.add(new Num(s));
			
			max = Math.max(max, Integer.parseInt(s));
		}
		
		while(!queue.isEmpty()) {
			s = queue.poll().s;
			if(Integer.parseInt(s) == max) {
				sb.append(s);
				++cnt;
				while(cnt < N - queue.size()) {
					sb.append(s);
					++cnt;
				}
			}
			else {
				sb.append(s);
				++cnt;
			}
		}
		
		System.out.println(sb);
	}

}

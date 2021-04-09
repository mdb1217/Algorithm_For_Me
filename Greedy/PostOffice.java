import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PostOffice {
	static class Post implements Comparable<Post> {
		int x;
		int people;
		
		Post(int a, int b) {
			x = a;
			people = b;
		}

		@Override
		public int compareTo(Post target) {
			return x - target.x;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// ±×¸®µð
		PriorityQueue<Post> queue = new PriorityQueue<>(); 
		int N;
		Post p;
		int x; 
		int people;
		long sum = 0;
		long comp = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			people = Integer.parseInt(st.nextToken());
			
			queue.add(new Post(x, people));
			sum += people;
		}
		
		if(sum % 2 == 1)
			sum = sum + 1;
		sum = sum / 2;
		
		
		while(!queue.isEmpty()) {
			p = queue.poll();
			comp += p.people;
			if(sum <= comp) {
				System.out.println(p.x);
				break;
			}
		}
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sensor {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 그리디
		// 총 선분은 N - 1 개 생길 것
		int N, K;
		int sum = 0;
		int prev, next;
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		PriorityQueue<Integer> distance_queue = new PriorityQueue<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i)
			queue.add(Integer.parseInt(st.nextToken()));
		
		prev = queue.poll();
		while(!queue.isEmpty()) {
			next = queue.poll();
			distance_queue.add(next - prev);
			prev = next;
		}
		
		for(int i = 0; i < N - K; ++i)
			sum += distance_queue.poll();
		
		System.out.println(sum);
	}

}

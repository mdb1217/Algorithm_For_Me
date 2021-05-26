import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PSStrongest {

	public static void main(String[] args) throws IOException {
		// ±×¸®µð
		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
		int N;
		int minus = 0;
		int limit;
		int sum = 0;
		int price;
		int comp;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			
			limit = Integer.parseInt(st.nextToken());
			price = Integer.parseInt(st.nextToken());
			
			if(sum <= limit) {
				sum += price;
				
				queue.add(price);
			}
			
			else {
				comp = queue.poll();
				if(comp <= price) {
					++minus;
					queue.add(comp);
				}
				else {
					if(sum - comp <= limit) {
						sum -= comp;
						sum += price;
						queue.add(price);
					}
					else
						queue.add(comp);
					++minus;
				}
			}
			
			if(minus >= 2)
				break;
		}
		
		System.out.println(minus >= 2? "Zzz" : "Kkeo-eok");
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JewelThief {
	static class Jewel implements Comparable<Jewel>{
		int weight;
		int value;
		
		Jewel(int a, int b) {
			weight = a;
			value = b;
		}
		
		@Override
		public int compareTo(Jewel target) {
			return target.value - value;
		}
		
	}
	
	static class JewelWeight implements Comparable<JewelWeight>{
		int weight;
		int value;
		
		JewelWeight(int a, int b) {
			weight = a;
			value = b;
		}
		
		@Override
		public int compareTo(JewelWeight target) {
			return weight - target.weight;
		}
		
	}

	public static void main(String[] args) throws IOException {
		// ±×¸®µð
		JewelWeight jw;
		int back = 0;
		long ans = 0;
		PriorityQueue<Integer> backpack = new PriorityQueue<>();
		PriorityQueue<JewelWeight> weight_queue = new PriorityQueue<>();
		PriorityQueue<Jewel> value_queue = new PriorityQueue<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, K;

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			weight_queue.add(new JewelWeight(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		for(int i = 0; i < K; ++i) 
			backpack.add(Integer.parseInt(br.readLine()));
		
		while(!backpack.isEmpty()) {
			if(back == 0)
				back = backpack.poll();
			
			if(!weight_queue.isEmpty()) {
				jw = weight_queue.poll();
				if(jw.weight > back) {
					weight_queue.add(jw);
					if(!value_queue.isEmpty())
						ans += value_queue.poll().value;
					back = 0;
				}
				else {
					value_queue.add(new Jewel(jw.weight, jw.value));
					if(backpack.isEmpty())
						ans += value_queue.poll().value;
				}
			}
			else {
				if(!value_queue.isEmpty()) {
					ans += value_queue.poll().value;
					back = 0;
				}
				else
					break;
			}
		}
		System.out.println(ans);
	}

}

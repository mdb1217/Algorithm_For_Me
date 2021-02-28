import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class MidTalk {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 우선순위 큐
		int N;
		int a;
		int mid;
		StringBuilder sb = new StringBuilder();

		PriorityQueue<Integer> min_heap = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> max_heap = new PriorityQueue<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
	
		mid = Integer.parseInt(br.readLine());
		sb.append(mid + "\n");
		for(int i = 1; i < N; i++) {
			a = Integer.parseInt(br.readLine());
			if(i % 2 == 0) {
				if(a > mid) {
					min_heap.add(mid);
					max_heap.add(a);
					mid = max_heap.poll();
				}
				else 
					min_heap.add(a);
			}
			else {
				if(a < mid) {
					max_heap.add(mid);
					min_heap.add(a);
					mid = min_heap.poll();
				}
				else 
					max_heap.add(a);
				
			}
			sb.append(mid + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Lecture {
	static class University implements Comparable<University> {
		int price;
		int day;

		University(int a, int b) {
			price = a;
			day = b;
		}
		@Override
		public int compareTo(University target) {
			if(this.day > target.day)
				return 1;
			else if(this.day == target.day) 
				return target.price - this.price;
			else 
				return -1;
	    }
		
	}
	public static void main(String[] args) throws IOException {
		// 그리디 알고리즘
		PriorityQueue<University> queue = new PriorityQueue<>();
		PriorityQueue<Integer> price_queue = new PriorityQueue<>();
		int n;
		int p;
		int d;
		int temp;
		int sum = 0;
		University u;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			p = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			queue.add(new University(p, d));
		}
		
		while(!queue.isEmpty()) {
			u = queue.poll();
			if(price_queue.size() < u.day) {
				price_queue.add(u.price);
				sum = u.price + sum;
			}
			else {
				temp = price_queue.poll();
				if(temp < u.price) {
					sum = sum - temp;
					sum = sum + u.price;
					price_queue.add(u.price);
				}
				else 
					price_queue.add(temp);
			}
		}
		
		System.out.println(sum);
	}

}

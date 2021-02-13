import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class ToiletRule {
	static class Person implements Comparable<Person> {
		int day;
		int busy;
		int line;
		boolean me;
		
		public Person(int a, int b, int c, boolean d) {
			day = a;
			busy = b;
			line = c;
			me = d;
		}
		
		@Override
		public int compareTo(Person target) {
			if(target.day - this.day > 0)
				return 1;
			else if(target.day - this.day == 0) {
				if(target.busy - this.busy > 0)
					return 1;
				else if(target.busy - this.busy == 0) {
					return this.line > target.line ? 1 : -1;
				}
				else
					return -1;
			}
			else 
				return -1;
	        
	    }
		
	}
	public static void main(String[] args) throws IOException {
		// 근무 일수 > 급한 정도 > 낮은 줄
		PriorityQueue<Person> queue = new PriorityQueue<>();
		Queue<Person>[] list;
		int N;
		int M;
		int K;
		int day;
		int busy;
		Person p;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		list = new Queue[M];
		for(int i = 0; i < M; i++) {
			list[i] = new LinkedList<Person>();
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			day = Integer.parseInt(st.nextToken());
			busy = Integer.parseInt(st.nextToken());
			if(i == K) {
				list[i % M].add(new Person(day, busy, i % M, true));
			}
			else {
				list[i % M].add(new Person(day, busy, i % M, false));
			}
			
			if(i < M)
				queue.add(list[i % M].poll());
		}
		
		for(int i = 0; i < N; i++) {
			p = queue.poll();
			if(p.me == true) {
				System.out.println(i);
				break;
			}
			
			if(!list[p.line].isEmpty())
				queue.add(list[p.line].poll());
		}
	}

}

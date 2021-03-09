import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Homework {
	static class Assignment implements Comparable<Assignment>{
		int day;
		int score;
		
		Assignment(int a, int b) {
			day = a;
			score = b;
		}
		
		@Override
		public int compareTo(Assignment target) {
			return this.day - target.day;
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		// �׸��� �˰���
		// �켱 ��¥������ ����~
		// �켱���� ť ����ؼ� ������¥ ������ ���ĳ�¥ ���� ���ϰ� ��ü~
		Assignment a;
		int N;
		int sum = 0;
		int time = 0;
		PriorityQueue<Assignment> day_queue = new PriorityQueue<>();
		PriorityQueue<Integer> score_queue = new PriorityQueue<>();
		StringTokenizer st;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			day_queue.add(new Assignment(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		for(int i = 0; i < N; i++) {
			a = day_queue.poll();
			if(time < a.day) {
				score_queue.add(a.score);
				++time;
			}
			else 
				score_queue.add(Math.max(score_queue.poll(), a.score));
		}
		
		for(int i : score_queue)
			sum = sum + i;
		System.out.println(sum);
	}

}

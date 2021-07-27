import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Prerequisite {
	static class Subject {
		int num;
		int time;
		
		Subject(int a, int b) {
			num = a;
			time = b;
		}
	}

	public static void main(String[] args) throws IOException {
		// 위상정렬
		StringBuilder sb = new StringBuilder();
		Subject s;
		int N, M;
		int require_cnt[];
		int ans[];
		int A, B;
		ArrayList<Queue<Integer>> list = new ArrayList<>();
		Queue<Subject> queue = new LinkedList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		require_cnt = new int[N + 1];
		ans = new int[N + 1];
		for(int i = 0; i < N + 1; ++i)
			list.add(new LinkedList<>());
		
		
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			list.get(A).add(B);
			++require_cnt[B];
		}
		
		for(int i = 1; i <= N; ++i) {
			if(require_cnt[i] == 0) {
				ans[i] = 1;
				queue.add(new Subject(i, 1));
			}
		}
		
		while(!queue.isEmpty()) {
			s = queue.poll();
			for(int num : list.get(s.num)) {
				--require_cnt[num];
				if(require_cnt[num] == 0) {
					ans[num] = s.time + 1;
					queue.add(new Subject(num, ans[num]));
				}
			}
		}
		
		for(int i = 1; i <= N; ++i)
			sb.append(ans[i]).append(" ");
		
		System.out.println(sb);
	}
}

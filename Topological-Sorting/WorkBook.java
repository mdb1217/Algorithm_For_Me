import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class WorkBook {

	public static void main(String[] args) throws IOException {
		// 위상 정렬
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		ArrayList<Queue<Integer>> list = new ArrayList<>();
		int require_prob_cnt[];
		int N, M;
		int A, B;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		require_prob_cnt = new int[N + 1];
		
		for(int i = 0; i < N + 1; i++)
			list.add(new LinkedList<>());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			list.get(A).add(B);
			++require_prob_cnt[B];
		}
		
		for(int i = 1; i <= N; i++) {
			if(require_prob_cnt[i] == 0)
				queue.add(i);
		}
		
		while(!queue.isEmpty()) {
			A = queue.poll();
			for(int i : list.get(A)) {
				--require_prob_cnt[i];
				if(require_prob_cnt[i] == 0)
					queue.add(i);
			}
			sb.append(A + " ");
		}
		
		System.out.println(sb.toString());
	}

}

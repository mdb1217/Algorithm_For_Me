import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class LineUp {

	public static void main(String[] args) throws IOException {
		// 위상 정렬
		StringBuilder sb = new StringBuilder();
		int num;
		int A, B;
		int N, M;
		Queue<Integer> queue = new LinkedList<>();
		ArrayList<Queue<Integer>> list = new ArrayList<>();
		int require_person_cnt[];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		require_person_cnt = new int[N + 1];
		
		for(int i = 0; i < N + 1; i++)
			list.add(new LinkedList<>());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			list.get(A).add(B);
			++require_person_cnt[B]; 
		}
		
		for(int i = 1; i <= N; i++) {
			if(require_person_cnt[i] == 0)
				queue.add(i);
		}
		
		while(!queue.isEmpty()) {
			num = queue.poll();
			sb.append(num + " ");
			for(int i : list.get(num)) {
				--require_person_cnt[i];
				if(require_person_cnt[i] == 0)
					queue.add(i);
			}
		}
		
		System.out.println(sb.toString());
	}

}

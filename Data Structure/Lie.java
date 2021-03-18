import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Lie {

	public static void main(String[] args) throws IOException {
		// 자료구조
		boolean Visited_Person[];
		boolean Visited_Party[];
		ArrayList<ArrayList<Integer>> people_list = new ArrayList<>();//인덱스는 사람 번호
		ArrayList<ArrayList<Integer>> party_list = new ArrayList<>();//인덱스는 파티 번호
		Queue<Integer> true_list = new LinkedList<>();
		int N, M;
		int ans;
		int t;
		int a;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = M;
		Visited_Party = new boolean[M + 1];
		Visited_Person = new boolean[N + 1];
		
		st = new StringTokenizer(br.readLine());
		t = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N + 1; i++) 
			people_list.add(new ArrayList<>());
		
		for(int i = 0; i < M + 1; i++)
			party_list.add(new ArrayList<>());
		
		for(int i = 0; i < t; i++) {
			a = Integer.parseInt(st.nextToken());
			true_list.add(a);
			Visited_Person[a] = true;
		}
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			for(int j = 0; j < t; j++) {
				a = Integer.parseInt(st.nextToken());
				people_list.get(a).add(i);
				party_list.get(i).add(a);
			}
		}
		
		while(!true_list.isEmpty()) {
			a = true_list.poll();
			for(int i : people_list.get(a)) {
				if(!Visited_Party[i]) {
					Visited_Party[i] = true;
					for(int people : party_list.get(i)) {
						if(!Visited_Person[people]) {
							Visited_Person[people] = true;
							true_list.add(people);
						}
					}
					ans = ans - 1;
				}
			}
		}
		System.out.println(ans);
	}

}

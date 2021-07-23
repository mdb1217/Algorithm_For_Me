import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DrugInvestigation {

	static int cnt = 0;
	static ArrayList<Queue<Integer>> list = new ArrayList<>();
	static boolean Visited[];
	public static void main(String[] args) throws IOException {
		// dfs
		boolean not_master[];
		int N, M;
		int B;
		String s;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Visited = new boolean[N];
		not_master = new boolean[N];
		for(int i = 0; i < N; ++i)
			list.add(new LinkedList<Integer>());
		
		for(int i = 0; i < M; ++i) {
			s = br.readLine();
			B = s.charAt(2) - 'A';
			list.get(s.charAt(0) - 'A').add(B);
			not_master[B] = true;
		}
		
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		for(int i = 0; i < B; ++i)
			Visited[st.nextToken().charAt(0) - 'A'] = true;
		
		for(int i = 0; i < N; ++i) {
			if(!not_master[i] && !Visited[i]) 
				dfs(i);
		}
		
		System.out.println(cnt);
	}
	
	static void dfs(int idx) {
		int a;
		Visited[idx] = true;
		while(!list.get(idx).isEmpty()) {
			a = list.get(idx).poll();
			if(!Visited[a]) {
				++cnt;
				dfs(a);
			}
		}
	}
}

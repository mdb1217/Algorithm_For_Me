import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CTPCanWinHansol {
	
	static class Num implements Comparable<Num>{
		int num;
		int cnt;
		
		Num(int a, int b) {
			num = a;
			cnt = b;
		}

		@Override
		public int compareTo(Num n) {
			return n.cnt - cnt;
		}
	}
	
	static boolean Visited[];
	static int link[];
	static int parent[];
	public static void main(String[] args) throws IOException {
		// MST
		Num n;
		PriorityQueue<Num> pq = new PriorityQueue<>();
		int A, B;
		int C, H;
		int max = 0;
		int N, M;
		int K;
		int cnt = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Visited = new boolean[N + 1];
		link = new int[N + 1];
		parent = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
			link[i] = 1;
		}
		
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			A = find(A);
			B = find(B);
			if(A == B)
				continue;
			union(A, B);
			link[A] += link[B];
		}
		
		for (int i = 1; i <= N; ++i) {
			int t = find(i);
			if (Visited[t]) 
				continue;
			Visited[t] = true;
			pq.add(new Num(t, link[t]));
		}

		
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		C = find(C);
		H = find(H);
		max = link[C];
		
		if(K != 0) {
			while(!pq.isEmpty()) {
				n = pq.poll();
			
				if(n.num != H && n.num != C) {
					max += n.cnt;
					++cnt;
				
					if(cnt >= K)
						break;
				}
			}
		}
		
		System.out.println(max);
	}

	static int find(int node) {
		if(node == parent[node])
			return node;
		parent[node] = find(parent[node]);
		return parent[node];
	}
	
	static void union(int start, int end) {
		parent[end] = start;
	}
}
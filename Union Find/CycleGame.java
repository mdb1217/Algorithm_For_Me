import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CycleGame {

	static int parent[];
	public static void main(String[] args) throws IOException {
		// 유니온 파인드
		boolean success = false;
		int start, end;
		int N, M;
		int ans = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N];
		
		for(int i = 0; i < N; ++i)
			parent[i] = i;
		
		for(int i = 1; i <= M; ++i) {
			st = new StringTokenizer(br.readLine());
			if(!success) {
				start = find(Integer.parseInt(st.nextToken()));
				end = find(Integer.parseInt(st.nextToken()));
				if(start == end) {
					success = true;
					ans = i;
					continue;
				}
				union(start, end);
			}
		}
		
		System.out.println(ans);
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

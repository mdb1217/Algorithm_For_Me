import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SocialNetworkingApplication {
	
	static int parent[];
	public static void main(String[] args) throws IOException {
		// 유니온 파인드
		StringBuilder sb = new StringBuilder();
		int T;
		int n, k, m;
		int u, v;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int i = 1; i <= T; ++i) {
			sb.append("Scenario ").append(i).append(":\n");
			n = Integer.parseInt(br.readLine());
			k = Integer.parseInt(br.readLine());
			
			parent = new int[n + 1];
			for(int j = 1; j <= n; ++j)
				parent[j] = j;
			
			for(int j = 0; j < k; ++j) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				u = find(u);
				v = find(v);
				if(u == v)
					continue;
				union(u, v);
			}
			
			m = Integer.parseInt(br.readLine());
			for(int j = 0; j < m; ++j) {
				st = new StringTokenizer(br.readLine());
				
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				sb.append(find(u) == find(v)? 1 : 0).append("\n");
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb);
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SetPresentation {

	static int parent[];
	public static void main(String[] args) throws IOException {
		// 유니온 파운드
		int n, m;
		int start, end;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parent = new int[n + 1];
		for(int i = 0; i <= n; ++i)
			parent[i] = i;
		
		for(int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 0) {
				start = find(Integer.parseInt(st.nextToken()));
				end = find(Integer.parseInt(st.nextToken()));
				if(start == end)
					continue;
				union(start, end);
			}
			else {
				if(find(Integer.parseInt(st.nextToken())) == find(Integer.parseInt(st.nextToken())))
					sb.append("YES").append("\n");
				else
					sb.append("NO").append("\n");
			}
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

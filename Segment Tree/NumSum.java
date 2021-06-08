import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NumSum {

	static long tree[];
	public static void main(String[] args) throws IOException {
		// ���׸�Ʈ Ʈ��
		StringBuilder sb = new StringBuilder();
		int height;
		long num[];
		int N, M;
		int index;
		long mod;
		int left, right;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		height = (int) (Math.ceil(Math.log(N) / Math.log(2)) + 1);
		tree = new long[(int) (Math.pow(2, height) + 1)];
		num = new long[N];
		
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			
			if(Integer.parseInt(st.nextToken()) == 0) {
				left = Integer.parseInt(st.nextToken()) - 1;
				right = Integer.parseInt(st.nextToken()) - 1;
				
				if(left > right)
					sb.append(sum(0, N - 1, 1, right, left)).append("\n");
				else
					sb.append(sum(0, N - 1, 1, left, right)).append("\n");
			}
			else {
				index = Integer.parseInt(st.nextToken()) - 1;
				mod = Long.parseLong(st.nextToken());
				update(0, N - 1, 1, index, mod, num[index]);
				num[index] = mod;
			}
		}
		
		System.out.print(sb);
	}

	static long sum(int start, int end, int node, int left, int right) {
		if(left > end || right < start)
			return 0;
		
		if(left <= start && right >= end)
			return tree[node];
		
		int mid = (start + end) / 2;
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}

	static void update(int start, int end, int node, int index, long mod, long minus) {
		if(index >= start && index <= end) {
			tree[node] -= minus;
			tree[node] += mod;
			if(start != end) {
				int mid = (start + end) / 2;
				update(start, mid, node * 2, index, mod, minus);
				update(mid + 1, end, node * 2 + 1, index, mod, minus);
			}
		}
	}
}

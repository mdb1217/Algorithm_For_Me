import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SectionSum {
	
	static long num[];
	static long tree[];
	public static void main(String[] args) throws IOException {
		// 세그먼트 트리
		StringBuilder sb = new StringBuilder();
		int height;
		int N, M, K;
		int index;
		long mod;
		int left, right;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		height = (int) (Math.ceil(Math.log(N) / Math.log(2)) + 1);
		tree = new long[(int) (Math.pow(2, height) + 1)];
		num = new long[N];
		for(int i = 0; i < N; ++i)
			 num[i] = Long.parseLong(br.readLine());
		initSegment(0, N - 1, 1);
		
		for(int i = 0; i < M + K; ++i) {
			st = new StringTokenizer(br.readLine());
			
			if(Integer.parseInt(st.nextToken()) == 1) {
				index = Integer.parseInt(st.nextToken()) - 1;
				mod = Long.parseLong(st.nextToken());
				update(0, N - 1, 1, index, mod, num[index]);
				num[index] = mod;
			}
			else {
				left = Integer.parseInt(st.nextToken()) - 1;
				right = Integer.parseInt(st.nextToken()) - 1;
				sb.append(sum(0, N - 1, 1, left, right)).append("\n");
			}
		}
		
		System.out.print(sb);
	}
	
	static long initSegment(int start, int end, int node) {
		if(start == end)
			return tree[node] = num[start];
		int mid = (start + end) / 2;
		return tree[node] = initSegment(start, mid, node * 2) + initSegment(mid + 1, end, node * 2 + 1);
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

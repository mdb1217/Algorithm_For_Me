import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PermAndQuery17 {

	static int num[];
	static int tree[];
	public static void main(String[] args) throws IOException {
		// 세그먼트 트리
		StringBuilder sb = new StringBuilder();
		int height;
		int N, M;
		int a;
		int index;
		int mod;
		int left, right;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		height = (int) (Math.ceil(Math.log(N) / Math.log(2)) + 1);
		tree = new int[(int) (Math.pow(2, height) + 1)];
		num = new int[N];
		for(int i = 0; i < N; ++i)
			num[i] = Integer.parseInt(st.nextToken());
		initSegment(0, N - 1, 1);
		
		M = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			
			if(a == 1) {
				index = Integer.parseInt(st.nextToken()) - 1;
				mod = Integer.parseInt(st.nextToken());
				update(0, N - 1, 1, index, mod);
				
			}
			else {
				left = Integer.parseInt(st.nextToken()) - 1;
				right = Integer.parseInt(st.nextToken()) - 1;
				sb.append(min(0, N - 1, 1, left, right)).append("\n");
			}
		}
		
		System.out.print(sb);
	}

	static int initSegment(int start, int end, int node) {
		if(start == end)
			return tree[node] = num[start];
		int mid = (start + end) / 2;
		return tree[node] = Math.min(initSegment(start, mid, node * 2), initSegment(mid + 1, end, node * 2 + 1));
	}
	
	static int min(int start, int end, int node, int left, int right) {
		if(left > end || right < start)
			return Integer.MAX_VALUE;
		
		if(left <= start && right >= end)
			return tree[node];
		
		int mid = (start + end) / 2;
		return Math.min(min(start, mid, node * 2, left, right), min(mid + 1, end, node * 2 + 1, left, right));
	}

	static int update(int start, int end, int node, int index, int mod) {
		if(index >= start && index <= end) {
			if(start != end) {
				int mid = (start + end) / 2;
				tree[node] = Math.min(update(start, mid, node * 2, index, mod), update(mid + 1, end, node * 2 + 1, index, mod));
			}
			else
				tree[node] = mod;
		}
		return tree[node];
	}
}

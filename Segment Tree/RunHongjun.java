import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RunHongjun {
	
	static int num[];
	static int tree[];
	public static void main(String[] args) throws IOException {
		// 세그먼트 트리
		StringBuilder sb = new StringBuilder();
		int height;
		int N, M;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		height = (int) (Math.ceil(Math.log(N) / Math.log(2)) + 1);
		tree = new int[(int) (Math.pow(2, height) + 1)];
		num = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i)
			 num[i] = Integer.parseInt(st.nextToken());
		
		initSegment(0, N - 1, 1);
		for(int i = M - 1; i < N - M + 1; ++i)
			sb.append(max(0, N - 1, 1, i - M + 1, i + M - 1)).append(" ");
		
		System.out.print(sb);
	}
	
	static int initSegment(int start, int end, int node) {
		if(start == end)
			return tree[node] = num[start];
		int mid = (start + end) / 2;
		return tree[node] = Math.max(initSegment(start, mid, node * 2), initSegment(mid + 1, end, node * 2 + 1));
	}
	
	static int max(int start, int end, int node, int left, int right) {
		if(left > end || right < start)
			return 0;
		
		if(left <= start && right >= end)
			return tree[node];
		
		int mid = (start + end) / 2;
		return Math.max(max(start, mid, node * 2, left, right), max(mid + 1, end, node * 2 + 1, left, right));
	}
}

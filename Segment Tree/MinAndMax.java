import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MinAndMax {
	static class MandM {
		int min;
		int max;
		
		MandM(int a, int b) {
			min = a;
			max = b;
		}
	}

	static int num[];
	static MandM tree[];
	public static void main(String[] args) throws IOException {
		// 세그먼트 트리
		StringBuilder sb = new StringBuilder();
		int N, M;
		int height;
		int left, right;
		MandM mm;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		height = (int) (Math.ceil(Math.log(N) / Math.log(2)) + 1);
		tree = new MandM[(int) (Math.pow(2, height) + 1)];
		num = new int[N];
		
		for(int i = 0; i < N; ++i)
			num[i] = Integer.parseInt(br.readLine());
		
		initSegment(0, N - 1, 1);
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			
			left = Integer.parseInt(st.nextToken()) - 1;
			right = Integer.parseInt(st.nextToken()) - 1;
			
			mm = getMAndM(0, N - 1, 1, left, right);
			sb.append(mm.min).append(" ").append(mm.max).append('\n');
		}
		
		System.out.print(sb);
	}

	static MandM initSegment(int start, int end, int node) {
		if(start == end)
			return tree[node] = new MandM(num[start], num[start]);
		int mid = (start + end) / 2;
		MandM a, b;
		a = initSegment(start, mid, node * 2);
		b = initSegment(mid + 1, end, node * 2 + 1);
		return tree[node] = new MandM(Math.min(a.min, b.min), Math.max(a.max, b.max));
	}
	
	static MandM getMAndM(int start, int end, int node, int left, int right) {
		if(left > end || right < start)
			return new MandM(Integer.MAX_VALUE, 0);
		
		if(left <= start && right >= end)
			return tree[node];
		
		int mid = (start + end) / 2;
		MandM a = getMAndM(start, mid, node * 2, left, right);
		MandM b = getMAndM(mid + 1, end, node * 2 + 1, left, right);
		return new MandM(Math.min(a.min, b.min), Math.max(a.max, b.max));
	}
}
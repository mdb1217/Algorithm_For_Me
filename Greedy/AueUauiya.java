import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AueUauiya {

	public static void main(String[] args) throws IOException {
		// 그리디 알고리즘
		// x는 정렬되서 들어옴
		int N;
		int x, y;
		int left = 0, right = 0;
		int sum = 0;
		int length;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		left = Integer.parseInt(st.nextToken());
		right = Integer.parseInt(st.nextToken());
		length = right - left;
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
		
			if(x <= right) {
				right = Math.max(y, right);
				length = right - left;
			}
			else {
				sum = sum + length;
				left = x;
				right = y;
				length = right - left;
			}
		}
		sum = sum + length;
		System.out.println(sum);
	}

}

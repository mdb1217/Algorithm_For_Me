import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LIS4 {
	static class Num {
		int length;
		String s;
		
		Num(int a, String b) {
			length = a;
			s = b;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 가장 긴 증가하는 부분 수열
		String sb;
		int ans = 0;
		String ans_s = "";
		int N;
		int temp;
		int A[];
		Num list[];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		A = new int[N];
		list = new Num[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; ++i) {
			sb = "";
			temp = 0;
			A[i] = Integer.parseInt(st.nextToken());
			
			for(int j = i - 1; j >= 0; j--) {
				if(A[j] < A[i]) {
					if(temp < list[j].length) {
						temp = list[j].length;
						sb = list[j].s;
					}
				}
			}
			list[i] = new Num(temp + 1, sb + A[i] + " ");
		
			if(ans < list[i].length) {
				ans = list[i].length;
				ans_s = list[i].s;
			}
		}
		
		System.out.println(ans);
		System.out.print(ans_s);
	}

}

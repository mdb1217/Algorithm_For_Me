import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class ThreeLiquid {

	public static void main(String[] args) throws IOException {
		// ÀÌºÐ Å½»ö
		boolean exit = false;
		long min = Long.MAX_VALUE;
		long sum;
		long num1 = 0, num2 = 0, num3 = 0;
		int left, right, mid;
		ArrayList<Long> list = new ArrayList<>();
		int N;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			list.add(Long.parseLong(st.nextToken()));
		Collections.sort(list);
		
		for(int i = 0; i < N - 2; i++) {
			if(list.get(i) > 0) {
				if(i + 2 < N) {
					if(list.get(i) + list.get(i + 1) + list.get(i + 2) < min) {
						num1 = list.get(i);
						num2 = list.get(i + 1);
						num3 = list.get(i + 2); 
					}
				}
				break;
			}
			for(int j = i + 1; j < N - 1; j++) {
				left = j + 1;
				right = N - 1;
				while(left <= right) {
					mid = (left + right) / 2;
					sum = list.get(i) + list.get(j) + list.get(mid);
					if(sum < 0) {
						left = mid + 1;
						sum = Math.abs(sum);
						if(sum < min) {
							num1 = list.get(i);
							num2 = list.get(j);
							num3 = list.get(mid);
							min = sum;
						}
					}
					else if(sum == 0) {
						num1 = list.get(i);
						num2 = list.get(j);
						num3 = list.get(mid);
						exit = true;
						break;
					}
					else {
						right = mid - 1;
						sum = Math.abs(sum);
						if(sum < min) {
							num1 = list.get(i);
							num2 = list.get(j);
							num3 = list.get(mid);
							min = sum;
						}
					}
				}
				if(exit)
					break;
			}
			if(exit)
				break;
		}
		System.out.println(num1 + " " + num2 + " " + num3);
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Liquid {

	public static void main(String[] args) throws IOException {
		// ÀÌºÐ Å½»ö
		boolean exit = false;
		int num1 = 0, num2 = 0;
		int min = Integer.MAX_VALUE;
		int comp;
		ArrayList<Integer> list = new ArrayList<>();
		int N;
		int left, right, mid;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i)
			list.add(Integer.parseInt(st.nextToken()));
		
		for(int i = 0; i < N; i++) {
			if(list.get(i) > 0) {
				if(i + 1 < N) {
					if(list.get(i) + list.get(i + 1) < min) {
						num1 = list.get(i);
						num2 = list.get(i + 1);
					}
				}
				break;
			}
			
			left = i + 1;
			right = N - 1;
			while(left <= right) {
				mid = (left + right) / 2;
				comp = list.get(i) + list.get(mid);
				if(comp < 0) {
					left = mid + 1;
					comp = Math.abs(comp);
					if(comp < min) {
						num1 = list.get(i);
						num2 = list.get(mid);
						min = comp;
					}
				}
				else if(comp == 0) {
					num1 = list.get(i);
					num2 = list.get(mid);
					exit = true;
					break;
				}
				else {
					right = mid - 1;
					comp = Math.abs(comp);
					if(comp < min) {
						num1 = list.get(i);
						num2 = list.get(mid);
						min = comp;
					}
				}
			}
			if(exit)
				break;
		}
		System.out.println(num1 + " " + num2);
	}

}

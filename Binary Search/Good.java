import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Good {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// ÀÌºÐÅ½»ö
		int N;
		int k;
		int comp;
		int left;
		int right;
		int prev = -1;
		boolean sw = false;
		int cnt = 0;
		ArrayList<Integer> list = new ArrayList<>();
		StringTokenizer st;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			k = Integer.parseInt(st.nextToken());
			
			list.add(k);
		}
		
		Collections.sort(list);
		
		for(int i = 0; i < N; i++) {
			comp = list.get(i);
			if(prev == comp) {
				if(sw)
					++cnt;
				continue;
			}
			sw = false;
			left = 0;
			right = N - 1;
			while(left < right) {
				if(comp == list.get(left) + list.get(right)) {
					if(right != i && left != i) {
						sw = true;
						break;
					}
					else {
						if(left == i)
							++left;
						else
							--right;
					}
				}
				else if(comp < list.get(left) + list.get(right)) {
					--right;
				}
				else {
					++left;
				}
			}
			if(sw)
				++cnt;
			prev = comp;
		}
		System.out.println(cnt);
	}

}

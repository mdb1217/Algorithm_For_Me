import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Teaching {

	static int k;
	static int max = 0;
	static char[] alpha = {'0', 'b', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'o', 'p', 'q', 'r', 's', 'u', 'v', 'w', 'x', 'y', 'z'};
	static ArrayList<Character> alpha_list = new ArrayList<>();
	static ArrayList<String> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		// antic 포함 5글자 미만이면 0 출력 26이면 모두 출력
		int n;
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < n; i++) 
			list.add(br.readLine());
		
		if(k < 5)
			System.out.println(0);
		else if(k == 26)
			System.out.println(n);
		else {
			alpha_list.add('a');
			alpha_list.add('n');
			alpha_list.add('t');
			alpha_list.add('i');
			alpha_list.add('c');
			combination(0, 0);
			System.out.println(max);
		}
	}

	static void combination(int prev, int d) {
		if(d == k - 5) {
			int count = 0;
			boolean possible;
			String s;
			for(int i = 0; i < list.size(); i++) {
				possible = true;
				s = list.get(i);
				for(int j = 4; j <= s.length() - 5; j++) {
					if(!alpha_list.contains(s.charAt(j))) {
						possible = false;
						break;
					}
				}
				if(possible)
					++count;
			}
			max = Math.max(count, max);
		}
		else {
			for(int i = prev + 1; i <= 21; i++) {
				alpha_list.add(alpha[i]);
				combination(i, d + 1);
				alpha_list.remove(alpha_list.size() - 1);
			}
		}
	}
}

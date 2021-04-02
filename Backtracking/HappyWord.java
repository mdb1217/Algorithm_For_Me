import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HappyWord {

	static long ans = 0;
	static int limit;
	static char word[];
	public static void main(String[] args) throws IOException {
		// 브루트 포스
		String s;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		s = br.readLine();
		limit = s.length();
		word = new char[s.length()];
		
		for(int i = 0; i < s.length(); ++i)
			word[i] = s.charAt(i);
		
		tracking(0, 0, 0, false, 1);
		System.out.println(ans);
	}
	
	static void tracking(int jaum, int moum, int length, boolean L, long num) {
		if(length == limit) {
			if(L)
				ans += num;
		}
		else {
			if(word[length] == '_') {
				if(jaum < 2) {
					tracking(jaum + 1, 0, length + 1, L, 20 * num);
					tracking(jaum + 1, 0, length + 1, true, num);
				}
				if(moum < 2)
					tracking(0, moum + 1, length + 1, L, 5 * num);
			}
			else {
				if(word[length] == 'A' || word[length] == 'E' || word[length] == 'E' || word[length] == 'I' || word[length] == 'O' || word[length] == 'U') {
					if(moum < 2)
						tracking(0, moum + 1, length + 1, L, num);
				}
				else {
					if(jaum < 2) {
						if(word[length] == 'L')
							tracking(jaum + 1, 0, length + 1, true, num);
						else
							tracking(jaum + 1, 0, length + 1, L, num);
					}
				}
			}
		}
	}

}

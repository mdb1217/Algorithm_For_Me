import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class StringBoom {

	public static void main(String[] args) throws IOException {
		// 문자열 알고리즘일듯
		boolean boom;
		String s;
		String comp;
		Stack<Character> stack = new Stack<Character>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		s = br.readLine();
		StringBuilder sb = new StringBuilder();
		comp = br.readLine();
		
		for(int i = 0; i < s.length(); i++) {
			boom = false;
			stack.push(s.charAt(i));
			if(stack.size() >= comp.length()) {
				boom = true;
				for(int j = 0; j < comp.length(); j++) {
					if(stack.get(stack.size() - j - 1) != comp.charAt(comp.length() - 1 - j)) {
						boom = false;
						break;
					}
				}
			}
			
			if(boom) {
				for(int j = 0; j < comp.length(); j++)
					stack.pop();
			}
		}
		
		for(Character c : stack)
			sb.append(c);
		
		System.out.println(sb.length() > 0? sb.toString() : "FRULA");
	}

}

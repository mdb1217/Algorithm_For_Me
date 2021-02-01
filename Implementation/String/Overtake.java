import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Overtake {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// in �߿��� ���ĵ� �κ��� �ǵ帮�� �ʰ� �ű⼭ ���� �ֵ鸸
		int N;
		int index = 0;
		int cnt = 0;
		ArrayList<String> in_list = new ArrayList<>();
		ArrayList<String> out_list = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			in_list.add(br.readLine());
		}
		
		for(int i = 0; i < N; i++) {
			out_list.add(br.readLine());
		}
		
		for(String i : out_list) {
			if(i.equals(in_list.get(index))) {
				++index;
			}
			else {
				in_list.remove(in_list.indexOf(i));
				++cnt;
			}
		}
		
		System.out.println(cnt);

	}

}

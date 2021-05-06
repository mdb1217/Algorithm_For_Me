import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class CandyCandy {

	public static void main(String[] args) throws IOException {
		// 그리디 알고리즘
		ArrayList<Integer> list = new ArrayList<>();
		int a;
		long temp;
		long sum = 0;
		long comp = 0;
		BigInteger mod = new BigInteger("2").pow(64);
		BigInteger n;
		BigInteger ans = new BigInteger("0");
		int M, N;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; ++i) {
			a = Integer.parseInt(br.readLine());
			list.add(a);
			sum += a;
		}
		
		temp = sum - M;
		Collections.sort(list);
		for(int i = 0; i < N; ++i) {
			comp = Math.min(list.get(i), temp / (N - i));
			temp -= comp;
			n = new BigInteger(Long.toString(comp)).pow(2);
			ans = ans.add(n.remainder(mod));
		}
		
		System.out.println(ans.remainder(mod));
	}

}
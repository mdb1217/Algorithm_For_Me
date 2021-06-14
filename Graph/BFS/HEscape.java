import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HEscape {
	static class Num {
		int n;
		int cnt;
		
		Num(int a, int b) {
			n = a;
			cnt = b;
		}
	}
	
	static int T;
	static boolean Visited[] = new boolean[100000];
	public static void main(String[] args) throws IOException {
		// bfs
		int N, G;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(N, G));
	}
	
	static String bfs(int N, int goal) {
		Num n;
		int num;
		Queue<Num> queue = new LinkedList<>();
		queue.add(new Num(N, 0));
		
		while(!queue.isEmpty()) {
			n = queue.poll();
			
			if(n.n == goal)
				return(Integer.toString(n.cnt));
			
			if(n.cnt < T) {
				if(n.n + 1 <= 99999 && !Visited[n.n + 1]) {
					queue.add(new Num(n.n + 1, n.cnt + 1));
					Visited[n.cnt + 1] = true;
				}
				
				if(n.n * 2 <= 99999 && !Visited[num = BAction(n.n * 2)]) {
					queue.add(new Num(num, n.cnt + 1));
					Visited[num] = true;
				}
			}
		}
		
		return "ANG";
	}
	
	static int BAction(int n) {
		int comp;
		int ans;
		int mod = Integer.toString(n).length();
		mod = (int) Math.pow(10, mod - 1);
		
		comp = (n / mod) - 1;
		ans = (n % mod);
		
		if(comp > 0)
			ans += comp * mod;
		
		return ans;
	}
}

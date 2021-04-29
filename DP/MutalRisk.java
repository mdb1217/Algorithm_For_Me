import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MutalRisk {
	static class SCV {
		int a;
		int b;
		int c;
		int n;
		int cnt;
		
		SCV(int d, int e, int f, int g, int h) {
			a = d;
			b = e;
			c = f;
			n = g;
			cnt = h;
		}
	}

	public static void main(String[] args) throws IOException {
		// DP
		int attack1[] = {9, 9, 3, 3, 1, 1};
		int attack2[] = {3, 1, 9, 1, 3, 9};
		int attack3[] = {1, 3, 1, 9, 9, 3};
		int attack2_1[] = {9, 3};
		int attack2_2[] = {3, 9};
		boolean Visited[][][] = new boolean[61][61][61];
		SCV s;
		boolean sw;
		int a, b, c;
		int i = 0;
		int n;
		int N;
		int list[] = new int[3];
		Queue<SCV> queue = new LinkedList<>();
		StringTokenizer st;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens())
			list[i++] = Integer.parseInt(st.nextToken());
		
		queue.add(new SCV(list[0], list[1], list[2], i, 0));
		
		while(!queue.isEmpty()) {
			s = queue.poll();
			if(s.n == 0) {
				System.out.println(s.cnt);
				break;
			}
			if(s.n == 3) {
				for(int k = 0; k < 6; ++k) {
					n = s.n;
					a = s.a - attack1[k];
					if(a <= 0) {
						a = 0;
						--n;
					}
					b = s.b - attack2[k];
					if(b <= 0) {
						b = 0;
						--n;
					}
					c = s.c - attack3[k];
					if(c <= 0) {
						c = 0;
						--n;
					}
					
					if(!Visited[a][b][c]) {
						Visited[a][b][c] = true;
						queue.add(new SCV(a, b, c, n, s.cnt + 1));
					}
				}
			}
			else if(s.n == 2) {
				for(int k = 0; k < 2; ++k) {
					n = s.n;
					a = s.a;
					b = s.b;
					c = s.c;
					sw = false;
					if(a != 0) {
						a = a - attack2_1[k];
						if(a <= 0) {
							a = 0;
							--n;
						}
						sw = true;
					}
					if(b != 0) {
						if(sw)
							b = b - attack2_2[k];
						else
							b = b - attack2_1[k];
						if(b <= 0) {
							b = 0;
							--n;
						}
					}
					if(c != 0) {
						c = c - attack2_2[k];
						if(c <= 0) {
							c = 0;
							--n;
						}
					}
					
					if(!Visited[a][b][c]) {
						Visited[a][b][c] = true;
						queue.add(new SCV(a, b, c, n, s.cnt + 1));
					}
				}
			}
			else {
				n = s.n;
				a = s.a;
				b = s.b;
				c = s.c;
				if(a != 0) {
					a = a - 9;
					if(a <= 0) {
						a = 0;
						--n;
					}
				}
				else if(b != 0){
					b = b - 9;
					if(b <= 0) {
						b = 0;
						--n;
					}
				}
				else {
					c = c - 9;
					if(c <= 0) {
						c = 0;
						--n;
					}
				}
				
				if(!Visited[a][b][c]) {
					Visited[a][b][c] = true;
					queue.add(new SCV(a, b, c, n, s.cnt + 1));
				}
			}
		}
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WormHole {
	static class Vertex {
		int vertex;
		int w;
		
		Vertex(int a, int b) {
			vertex = a;
			w = b;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 벨만 포드 알고리즘
		// 음의 사이클 생기는 순간 바로 YES?
		boolean yes;
		int N, M, W;
		int n, m, w;
		int T;
		int dist[];
		ArrayList<ArrayList<Vertex>> list = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			yes = false;
			list.clear();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			dist = new int[N + 1];
			Arrays.fill(dist, 30000000);
			dist[1] = 0;
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			for(int j = 0; j < N + 1; j++)
				list.add(new ArrayList<>());
			
			for(int j = 0; j < M; j++) {//도로
				st = new StringTokenizer(br.readLine());
				n = Integer.parseInt(st.nextToken());
				m = Integer.parseInt(st.nextToken());
				w = Integer.parseInt(st.nextToken());
				
				list.get(n).add(new Vertex(m, w));
				list.get(m).add(new Vertex(n, w));
			}
			
			for(int j = 0; j < W; j++) {//웜홀
				st = new StringTokenizer(br.readLine());
				n = Integer.parseInt(st.nextToken());
				m = Integer.parseInt(st.nextToken());
				w = Integer.parseInt(st.nextToken());
				
				list.get(n).add(new Vertex(m, -w));
			}
			
			for(int j = 0; j < N - 1; j++) {
				for(int k = 1; k <= N; k++) {
					for(Vertex v : list.get(k)) {
						m = v.vertex;
						w = v.w;
					
						if(dist[m] > dist[k] + w)
							dist[m] = dist[k] + w;
					}
				}
			}
			
			for(int j = 1; j <= N; j++) {
				for(Vertex v : list.get(j)) {
					m = v.vertex;
					w = v.w;
				
					if(dist[m] > dist[j] + w) {
						yes = true;
						break;
					}
				}
				if(yes)
					break;
			}
			
			System.out.println(yes? "YES" : "NO");
		}
	}

}

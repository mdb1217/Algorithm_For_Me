import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HideAndSeek2 {
	
	static int K;
	static int Visited[] = new int[200001];
	static int Count[] = new int[200001];
	public static void main(String[] args) throws IOException {
		// BFS
		int N;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(N == K) {
			System.out.println(0);
			System.out.println(1);
		}
		else {
			BFS(N);
			System.out.println(Visited[K]);
			System.out.println(Count[K]);
		}
	}
	
	static void BFS(int start) {
		int vertex;
		int time = 0;
		int limit;
		Visited[start] = -1;
		Count[start] = 1;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		
		while(true) {
			++time;
			limit = queue.size();
			for(int i = 0; i < limit; i++) {
				vertex = queue.poll();
				for(int j = 0; j < 3; j++) {
					if(j == 0) {
						if(vertex < K) {
							if(Visited[vertex + 1] == 0 || Visited[vertex + 1] == time) {
								Visited[vertex + 1] = time;
								Count[vertex + 1] = Count[vertex + 1] + Count[vertex];
								if(!queue.contains(vertex + 1))
									queue.add(vertex + 1);
							}
						}
					}
					else if(j == 1) {
						if(vertex > K) {
							if(Visited[vertex - 1] == 0 || Visited[vertex - 1] == time) {
								Visited[vertex - 1] = time;
								Count[vertex - 1] = Count[vertex - 1] + Count[vertex];
								if(!queue.contains(vertex - 1))
									queue.add(vertex - 1);
							}
						}
					}
					else {
						if(vertex < K) {
							if(Visited[vertex * 2] == 0 || Visited[vertex * 2] == time) {
								Visited[vertex * 2] = time;
								Count[vertex * 2] = Count[vertex * 2] + Count[vertex];
								if(!queue.contains(vertex * 2))
									queue.add(vertex * 2);
							}
						}
					}
				}
			}
			if(Visited[K] > 0)
				break;
		}
	}

}

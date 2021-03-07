import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HideAndSeek3 {
	static class Vertex implements Comparable<Vertex>{
		int num;
		int time;
		
		Vertex(int a, int b) {
			num = a;
			time = b;
		}

		@Override
		public int compareTo(Vertex target) {
			return this.time - target.time;
		}
		
	}
	
	static int K;
	static boolean Visited[] = new boolean[200001];
	public static void main(String[] args) throws IOException {
		// BFS
		int N;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(N == K) 
			System.out.println(0);
		else 
			BFS(N);
	}
	
	static void BFS(int start) {
		Vertex vertex;
		int time = 0;
		int num;
		PriorityQueue<Vertex> queue = new PriorityQueue<>();
		queue.add(new Vertex(start, time));
		
		while(!queue.isEmpty()) {
			vertex = queue.poll();
			time = vertex.time;
			if(!Visited[vertex.num] && vertex.num < K && vertex.num != 0) {
				num = vertex.num;
				while(num <= K) {
					num = num * 2;
					if(!Visited[num]) {
						Visited[num] = true;
						queue.add(new Vertex(num, time));
					}
				}
			}
			Visited[vertex.num] = true;
			if(Visited[K]) {
				System.out.println(time);
				break;
			}
			
			if(vertex.num < K) {
				if(!Visited[vertex.num + 1]) 
					queue.add(new Vertex(vertex.num + 1, time + 1));
			}
				
			if(vertex.num > 0) {
				if(!Visited[vertex.num - 1]) 
					queue.add(new Vertex(vertex.num - 1, time + 1));
			}
		}
	}

}
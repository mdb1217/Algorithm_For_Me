import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Chairman {
	static class Node {
		int x;
		int cnt;
		
		Node(int a, int b) {
			x = a;
			cnt = b;
		}
	}
	static ArrayList<ArrayList<Integer>> Map = new ArrayList<>();
	static int Visited[];
	public static void main(String[] args) throws IOException {
		//bfs
		ArrayList<Integer> min_list = new ArrayList<>();
		int n;
		int x, y;
		int comp;
		int min = 0;
		
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n + 1; i++) {
			Map.add(new ArrayList<Integer>());
		}
		while(true) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			if(x == -1 && y == -1)
				break;
			Map.get(x).add(y);
			Map.get(y).add(x);
		}
		
		Visited = new int[n + 1];
		min = bfs(1);
		min_list.add(1);
		for(int i = 2; i <= n; i++) {
			Visited = new int[n + 1];
			comp = bfs(i);
			if(comp <= min) {
				if(comp < min) {
					min_list.clear();
					min_list.add(i);
					min = comp;
				}
				else 
					min_list.add(i);
			}
		}
		System.out.println(min + " " + min_list.size());
		for(int i : min_list)
			System.out.print(i + " ");
	}
	
	static int bfs(int x) {
		int max = 0;
		int n = x;
		int cnt = 0;
		ArrayList<Node> list = new ArrayList<>(); 
		list.add(new Node(n, cnt));
		Visited[n] = 1;
		
		while(!list.isEmpty()) {
			n = list.get(0).x;
			cnt = list.get(0).cnt + 1;
			list.remove(0);
			for(int i : Map.get(n)) {
				if(Visited[i] == 0) {
					list.add(new Node(i, cnt));
					max = Math.max(max, cnt);
					Visited[i] = 1;
				}
			}
		}
		return max;
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TermProject {
	static boolean sw;
	static int end;
	static int start;
	static int cnt;
	static ArrayList<Integer> list;
	static ArrayList<Integer> temp_list = new ArrayList<>();
	static int Visited[];
	public static void main(String[] args) throws IOException {
		// dfs 문제
		// 사이클이 생기면 그룹 하나 생성
		int T;
		int n;
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			list = new ArrayList<>();
			list.add(0);
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			cnt = n;
			Visited = new int[n + 1];
			for(int j = 1; j <= n; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
		
			for(int j = 1; j <= n; j++) {
				if(Visited[j] == 0) {
					temp_list.clear();
					sw = true;
					cycle(j);
				}
			}
			System.out.println(cnt);
		}
	}
	
	static void cycle(int n) {
		Visited[n] = 1;//한 번 사이클이 될 수 없는 애는 계속 될 수 없고, 사이클이 될 수 있는 애는 계속 될 수 있음. 따라서 visited처리로 중복 탐색 방지
		temp_list.add(n);
		int m = list.get(n);
		
		if(Visited[m] == 0) {
			cycle(m);
			if(sw)
				--cnt;//순환의 시작점인 애까지만 마이너스
			if(n == end) { //cycle있는 부분들만 마이너스하기위해서
				sw = false;
			}
		}
		else {
			end = m;
			if(temp_list.contains(m)) {
				--cnt;
				if(n == end)//자기 자신으로 순환되는 애 처리하기 위해서
					sw = false;
			}
			else
				sw = false;
		}
	}

}
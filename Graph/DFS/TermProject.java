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
		// dfs ����
		// ����Ŭ�� ����� �׷� �ϳ� ����
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
		Visited[n] = 1;//�� �� ����Ŭ�� �� �� ���� �ִ� ��� �� �� ����, ����Ŭ�� �� �� �ִ� �ִ� ��� �� �� ����. ���� visitedó���� �ߺ� Ž�� ����
		temp_list.add(n);
		int m = list.get(n);
		
		if(Visited[m] == 0) {
			cycle(m);
			if(sw)
				--cnt;//��ȯ�� �������� �ֱ����� ���̳ʽ�
			if(n == end) { //cycle�ִ� �κе鸸 ���̳ʽ��ϱ����ؼ�
				sw = false;
			}
		}
		else {
			end = m;
			if(temp_list.contains(m)) {
				--cnt;
				if(n == end)//�ڱ� �ڽ����� ��ȯ�Ǵ� �� ó���ϱ� ���ؼ�
					sw = false;
			}
			else
				sw = false;
		}
	}

}
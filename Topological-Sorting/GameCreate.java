import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GameCreate {
	static class Building implements Comparable<Building> {
		int num;
		int prev_time;
		
		Building(int a, int b) {
			num = a;
			prev_time = b;
		}

		@Override
		public int compareTo(Building target) {
			return prev_time - target.prev_time;
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		// ť 2���̿�(�ݺ� ��������, �ǹ� �Ǽ� �� ���� �ǹ��� ���� �� �ִ� ��)
		// arraylist �ϳ� �̿�(�� ��ȣ �ǹ� �Ǽ��� �� ���� �ǹ� �ʿ�����)
		int N;
		int a;
		Building b;
		int build_time[];
		int ans[];
		int cnt;
		PriorityQueue<Building> queue = new PriorityQueue<>();
		ArrayList<Integer> require_build_cnt = new ArrayList<>();//�ε����� �ǹ�
		ArrayList<Queue<Integer>> can_build = new ArrayList<>();
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		build_time = new int[N + 1];
		ans = new int[N + 1];
		
		for(int i = 0; i < N + 1; i++)
			can_build.add(new LinkedList<>());
		
		require_build_cnt.add(0);
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			build_time[i] = Integer.parseInt(st.nextToken());
			cnt = 0;
			while((a = Integer.parseInt(st.nextToken())) != -1) {
				can_build.get(a).add(i);
				++cnt;
			}
			require_build_cnt.add(cnt);
			if(cnt == 0) {
				queue.add(new Building(i, build_time[i]));
				ans[i] = build_time[i];
			}
		}
		
		while(!queue.isEmpty()) {
			b = queue.poll();
			for(int i : can_build.get(b.num)) {
				require_build_cnt.set(i, require_build_cnt.get(i) - 1);
				if(require_build_cnt.get(i) == 0) {
					queue.add(new Building(i, ans[b.num] + build_time[i]));
					ans[i] = ans[b.num] + build_time[i];
				}
			}
		}
		
		for(int i = 1; i <= N; i++)
			System.out.println(ans[i]);
	}

}

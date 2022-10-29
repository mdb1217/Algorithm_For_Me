import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FriendExpense {

    static int[] parent;
    static long[] expense;

    public static void main(String[] args) throws IOException {
        boolean[] visited;
        boolean success = true;
        int N, M, k;
        long sum = 0;
        int start, end;
        int n;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        expense = new long[N + 1];
        visited = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            parent[i] = i;
            expense[i] = Long.parseLong(st.nextToken());
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            if (start == end)
                continue;
            if(expense[start] < expense[end])
                union(start, end);
            else
                union(end, start);
        }

        for (int i = 1; i <= N; ++i) {
            n = find(i);
            if (visited[n]) continue;
            sum += expense[n];
            if (sum > k) {
                success = false;
                break;
            }
            visited[n] = true;
        }

        System.out.println(success ? sum : "Oh no");
    }

    static int find(int node) {
        if (node == parent[node])
            return node;
        parent[node] = find(parent[node]);
        return parent[node];
    }

    static void union(int start, int end) {
        parent[end] = start;
    }
}

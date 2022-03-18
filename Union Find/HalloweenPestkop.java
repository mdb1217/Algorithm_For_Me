import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HalloweenPestkop {

    static int[] parent;
    public static void main(String[] args) throws IOException {
        int N, M, K;
        int u, v;
        boolean[] visited;
        int[] max;
        int[] candy;
        int[] person;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        candy = new int[N + 1];
        person = new int[N + 1];
        visited = new boolean[N + 1];
        max = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            parent[i] = i;
            candy[i] = Integer.parseInt(st.nextToken());
            person[i] = 1;
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());

            u = find(Integer.parseInt(st.nextToken()));
            v = find(Integer.parseInt(st.nextToken()));

            if (u == v)
                continue;

            union(u, v);
            candy[u] += candy[v];
            person[u] += person[v];
            visited[v] = true;
        }

        for (int i = 1; i <= N; ++i) {
            if (visited[i])
                continue;
            for (int j = K - 1; j >= person[i]; --j)
                max[j] = max[j] = Math.max(max[j], max[j - person[i]] + candy[i]);
            visited[i] = true;
        }

        System.out.println(max[K - 1]);
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

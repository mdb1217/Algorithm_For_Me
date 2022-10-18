import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BotheringHaegang {

    static int[] parent;
    public static void main(String[] args) throws IOException {
        // Union Find
        int a, b;
        int N, M;
        int prev;
        int comp;
        int cnt = 0;
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; ++i)
            parent[i] = i;

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            a = find(Integer.parseInt(st.nextToken()));
            b = find(Integer.parseInt(st.nextToken()));

            if (a == b)
                continue;
            union(a, b);
        }

        st = new StringTokenizer(br.readLine());
        prev = find(Integer.parseInt(st.nextToken()));
        while (st.hasMoreTokens()) {
            comp = find(Integer.parseInt(st.nextToken()));
            if (comp == prev)
                continue;
            prev = comp;
            ++cnt;
        }

        System.out.println(cnt);
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IHateGoinmul {
    static class Water {
        int goin;
        int chungjung;

        Water(int a, int b) {
            goin = a;
            chungjung = b;
        }
    }

    static int[] parent;
    static Water[] water;
    public static void main(String[] args) throws IOException {
        // 유니온 파운드
        StringBuilder sb = new StringBuilder();
        int start, end;
        int N, M, Q;
        Water w;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        water = new Water[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            parent[i] = i;
            if(Integer.parseInt(st.nextToken()) == 0)
                water[i] = new Water(1, 0);
            else
                water[i] = new Water(0, 1);
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            start = find(Integer.parseInt(st.nextToken()));
            end = find(Integer.parseInt(st.nextToken()));
            if (start == end)
                continue;
            union(start, end);
        }

        for(int i = 0; i < Q; ++i) {
            w = water[find(Integer.parseInt(br.readLine()))];
            sb.append(w.chungjung > w.goin? 1 : 0).append('\n');
        }

        System.out.print(sb);
    }

    static int find(int node) {
        if (node == parent[node])
            return node;
        parent[node] = find(parent[node]);
        return parent[node];
    }

    static void union(int start, int end) {
        parent[end] = start;
        water[start].chungjung += water[end].chungjung;
        water[start].goin += water[end].goin;
    }
}

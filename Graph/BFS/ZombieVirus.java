import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ZombieVirus {
    static class Graph {
        int x;
        int y;
        int virus;

        Graph(int a, int b, int c) {
            x = a;
            y = b;
            virus = c;
        }
    }

    static int[] ans = new int[4];
    static int N, M;
    static Queue<Graph> queue = new LinkedList<>();
    static int[][] Map;
    static int[][] Visited;
    static int[] op_x = {0, 0, 1, -1};
    static int[] op_y = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        int count = 1;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Map = new int[N][M];
        Visited = new int[N][M];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                Map[i][j] = Integer.parseInt(st.nextToken());

                if (Map[i][j] != 0 && Map[i][j] != -1) {
                    queue.add(new Graph(i, j, Map[i][j]));
                    ++ans[Map[i][j]];
                    Visited[i][j] = 1;
                }
            }
        }
        while (!queue.isEmpty()) {
            VirusBfs(++count);
        }

        sb.append(ans[1]).append(" ").append(ans[2]).append(" ").append(ans[3]);
        System.out.println(sb);
    }

    static void VirusBfs(int count) {
        Graph g;
        int x, y, virus;
        int limit = queue.size();
        int op_X, op_Y;

        for (int i = 0; i < limit; i++) {
            g = queue.poll();
            x = g.x;
            y = g.y;
            virus = g.virus;
            if (Map[x][y] != 3) {
                for (int j = 0; j < 4; j++) {
                    op_X = op_x[j] + x;
                    op_Y = op_y[j] + y;
                    if (op_X >= 0 && op_X < N && op_Y >= 0 && op_Y < M) {
                        if (Map[op_X][op_Y] != -1) {
                            if (Map[op_X][op_Y] == 0) {
                                queue.add(new Graph(op_X, op_Y, virus));
                                Map[op_X][op_Y] = virus;
                                Visited[op_X][op_Y] = count;
                                ++ans[virus];
                            } else if (Map[op_X][op_Y] != virus && Map[op_X][op_Y] != 3 && Visited[op_X][op_Y] == count) {
                                Map[op_X][op_Y] = 3;
                                ++ans[3];
                                if (virus == 1)
                                    --ans[2];
                                else
                                    --ans[1];
                            }
                        }
                    }
                }
            }
        }
    }
}

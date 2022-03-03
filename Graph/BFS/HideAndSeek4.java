import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class HideAndSeek4 {
    static class Vertex {
        int num;
        int time;

        Vertex(int a, int b) {
            num = a;
            time = b;
        }
    }

    static int K;
    static boolean[] Visited = new boolean[200001];
    static int[] from = new int[200001];
    public static void main(String[] args) throws IOException {
        // BFS
        int N;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0);
            System.out.println(N);
        }
        else
            BFS(N);
    }

    static void BFS(int start) {
        Vertex vertex;
        int time = 0;
        Queue<Vertex> queue = new LinkedList();
        queue.add(new Vertex(start, time));

        while (!queue.isEmpty()) {
            vertex = queue.poll();
            time = vertex.time;

            if (vertex.num < K && vertex.num != 0 && !Visited[vertex.num * 2]) {
                Visited[vertex.num * 2] = true;
                from[vertex.num * 2] = vertex.num;
                if(isEnd(time + 1, start))
                    break;
                queue.add(new Vertex(vertex.num * 2, time + 1));
            }

            if (vertex.num < K && !Visited[vertex.num + 1]) {
                Visited[vertex.num + 1] = true;
                from[vertex.num + 1] = vertex.num;
                if(isEnd(time + 1, start))
                    break;
                queue.add(new Vertex(vertex.num + 1, time + 1));
            }

            if (vertex.num > 0 && !Visited[vertex.num - 1]) {
                Visited[vertex.num - 1] = true;
                from[vertex.num - 1] = vertex.num;
                if(isEnd(time + 1, start))
                    break;
                queue.add(new Vertex(vertex.num - 1, time + 1));
            }
        }
    }

    static Boolean isEnd(int time, int start) {
        int num;
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack();
        if (Visited[K]) {
            System.out.println(time);
            stack.add(K);
            num = K;
            while(from[num] != start) {
                stack.add(from[num]);
                num = from[num];
            }
            stack.add(start);
            while (!stack.isEmpty())
                sb.append(stack.pop()).append(" ");

            System.out.println(sb);

            return true;
        }
        return false;
    }
}

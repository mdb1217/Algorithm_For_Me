import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class FileMerge3 {
    public static void main(String[] args) throws IOException {
        // 그리디
        PriorityQueue<Long> queue;
        int T;
        int K;
        long sum;
        long operand1, operand2;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; ++i) {
            queue = new PriorityQueue<>();
            sum = 0;
            K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; ++j)
                queue.add(Long.parseLong(st.nextToken()));

            while (!queue.isEmpty()) {
                operand1 = queue.poll();
                if (!queue.isEmpty()) {
                    operand2 = queue.poll();
                    sum += operand1 + operand2;
                    queue.add(operand1 + operand2);
                } else
                    sb.append(sum).append('\n');
            }
        }

        System.out.print(sb);
    }
}

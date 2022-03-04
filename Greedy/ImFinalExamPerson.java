import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ImFinalExamPerson {
    static class Class implements Comparable<Class> {
        int score;
        int plus;

        Class(int a, int b) {
            score = a;
            plus = b;
        }

        @Override
        public int compareTo(Class target) {
            if (this.plus < target.plus)
                return 1;
            else if (this.plus == target.plus)
                return this.score - target.score;
            else
                return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        // 그리디
        int[] scoreList;
        PriorityQueue<Class> queue = new PriorityQueue<>();
        PriorityQueue<Integer> remainderQueue = new PriorityQueue<>(Comparator.reverseOrder());
        int N, M;
        int sum = 0;
        int a, b;
        int num;
        int time;
        int remainder;
        Class c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        time = 24 * N;
        scoreList = new int[M + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; ++i) {
            a = Integer.parseInt(st.nextToken());
            scoreList[i] = a;
            sum += a;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; ++i) {
            a = Integer.parseInt(st.nextToken());
            queue.add(new Class(scoreList[i], a));
        }

        while (!queue.isEmpty()) {
            c = queue.poll();
            remainder = 100 - c.score;
            a = remainder % c.plus;
            b = remainder / c.plus;

            while (!remainderQueue.isEmpty() && time > 0) {
                num = remainderQueue.poll();
                if (num > c.plus) {
                    --time;
                    sum += num;
                } else {
                    remainderQueue.add(num);
                    break;
                }
            }

            if (time >= b) {
                time -= b;
                sum += remainder - a;
                if(a != 0)
                    remainderQueue.add(a);
            } else {
                sum += time * c.plus;
                time = 0;
            }

            if (time == 0)
                break;
        }

        while (time > 0 && !remainderQueue.isEmpty()) {
            sum += remainderQueue.poll();
            --time;
        }
        System.out.println(sum);
    }
}

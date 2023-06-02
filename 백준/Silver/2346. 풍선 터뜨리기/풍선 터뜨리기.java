import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Deque<Balloon> deque = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            deque.add(new Balloon(i, Integer.parseInt(st.nextToken())));
        }
        StringBuilder answer = new StringBuilder();
        final String BLANK = " ";
        while (!deque.isEmpty()) {
            Balloon balloon = deque.poll();
            answer.append(balloon.idx).append(BLANK);
            N--;
            if (N == 0) break;
            if (balloon.num > 0) {
                for (int i = 1; i < balloon.num; i++) {
                    deque.add(deque.poll());
                }
            } else {
                for (int i = 0; i < -balloon.num; i++) {
                    deque.offerFirst(deque.pollLast());
                }
            }
        }
        System.out.println(answer);
    }

    private static class Balloon {
        int idx;
        int num;

        private Balloon(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }
}
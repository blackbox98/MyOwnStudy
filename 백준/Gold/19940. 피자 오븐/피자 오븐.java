import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] btnValue = new int[]{60, 10, -10, 1, -1};
    static int[][] pressBtn = new int[61][6];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        pizzaOven();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] answer = Arrays.copyOf(pressBtn[N % 60], 6);
            answer[0] += N / 60;
            for (int i = 0; i < 5; i++) {
                sb.append(answer[i]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void pizzaOven() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[6]);
        boolean[] v = new boolean[61];
        v[0] = true;
        while (!queue.isEmpty()) {
            int[] curBtn = queue.poll();
            for (int i = 4; i >= 0; i--) {
                int nextTime = curBtn[5] + btnValue[i];
                if (0 > nextTime || nextTime > 60 || v[nextTime]) continue;
                int[] nextBtn = Arrays.copyOf(curBtn, 6);
                nextBtn[i]++;
                nextBtn[5] = nextTime;
                queue.offer(nextBtn);
                v[nextTime] = true;
                pressBtn[nextTime] = nextBtn;
            }
        }
    }
}
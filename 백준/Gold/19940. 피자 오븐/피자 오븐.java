import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] btnValue = new int[]{60, 10, -10, 1, -1};
    static String[] pressBtn = new String[61];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        pizzaOven();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] answer = new int[5];
            answer[0] = N / 60;
            N %= 60;
            for (int i = 0; i < 5; i++) {
                answer[i] += pressBtn[N].charAt(i) - '0';
                sb.append(answer[i]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void pizzaOven() {
        Arrays.fill(pressBtn, "99999");
        pressBtn[0] = "00000";
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int time = queue.poll();
            for (int i = 4; i >= 0; i--) {
                int nextTime = time + btnValue[i];
                if (nextTime < 0 || nextTime > 60) continue;
                String curBtn = pressBtn[time];
                char btn = (char) (curBtn.charAt(i) + 1);
                curBtn = curBtn.substring(0, i) + btn + curBtn.substring(i + 1);
                if (compareBtn(curBtn, pressBtn[nextTime])) {
                    pressBtn[nextTime] = curBtn;
                    queue.offer(nextTime);
                }
            }
        }
    }
    
    private static boolean compareBtn(String a, String b) {
        int cntA = 0;
        int cntB = 0;
        for (int i = 0; i < 5; i++) {
            cntA += a.charAt(i) - '0';
            cntB += b.charAt(i) - '0';
        }
        if (cntA == cntB) return a.compareTo(b) < 0;
        else return cntA < cntB;
    }
}
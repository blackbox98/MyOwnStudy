import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] schedule = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N + 1];
        for (int day = 0; day < N; day++) {
            if (day + schedule[day][0] <= N) {
                dp[day + schedule[day][0]] = Math.max(dp[day + schedule[day][0]], dp[day] + schedule[day][1]);
            }
            dp[day + 1] = Math.max(dp[day + 1], dp[day]);
        }
        System.out.println(dp[N]);
    }
}
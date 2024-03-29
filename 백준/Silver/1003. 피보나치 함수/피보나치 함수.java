import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] dp = new int[N + 1][2];
            dp[0][0] = 1;
            dp[0][1] = 0;
            if (N == 0) {
                System.out.println(dp[N][0] + " " + dp[N][1]);
                continue;
            }
            dp[1][0] = 0;
            dp[1][1] = 1;
            for (int i = 2; i <= N; i++) {
                dp[i][0] = dp[i - 2][0] + dp[i - 1][0];
                dp[i][1] = dp[i - 2][1] + dp[i - 1][1];
            }
            System.out.println(dp[N][0] + " " + dp[N][1]);
        }
    }
}

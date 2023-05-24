import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] capacity = new int[n];
        for (int i = 0; i < n; i++) {
            capacity[i] = Integer.parseInt(br.readLine());
        }
        int[] dp = new int[n];
        dp[0] = capacity[0];
        if (n >= 2) {
            dp[1] = capacity[0] + capacity[1];
            if (n >= 3) {
                dp[2] = Math.max(dp[1], Math.max(capacity[0] + capacity[2], capacity[1] + capacity[2]));
            }
        }
        for (int i = 3; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + capacity[i], dp[i - 3] + capacity[i - 1] + capacity[i]));
        }
        System.out.println(dp[n - 1]);
    }
}
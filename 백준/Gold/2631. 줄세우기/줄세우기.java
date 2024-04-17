import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] child = new int[N];
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            child[i] = Integer.parseInt(br.readLine());
        }
        int LIS = 0;
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (child[i] <= child[j] || dp[i] >= dp[j] + 1) continue;
                dp[i] = dp[j] + 1;
            }
            LIS = Math.max(LIS, dp[i]);
        }
        System.out.println(N - LIS);
    }
}
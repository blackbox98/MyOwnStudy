import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] tree = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            tree[i] = Integer.parseInt(br.readLine());
        }
        int[][][] dp = new int[T + 1][W + 1][3];
        if (tree[1] == 1) dp[1][0][1] = 1;
        else dp[1][1][2] = 1;
        for (int t = 2; t <= T; t++) {
            int curTree = tree[t];
            if (curTree == 1) {
                dp[t][0][1] = dp[t - 1][0][1] + 1;
                dp[t][0][2] = dp[t - 1][0][2];
                for (int w = 1; w <= W; w++) {
                    dp[t][w][1] = Math.max(dp[t - 1][w][1], dp[t - 1][w - 1][2]) + 1;
                    dp[t][w][2] = Math.max(dp[t - 1][w - 1][1], dp[t - 1][w][2]);
                }
            } else {
                dp[t][0][1] = dp[t - 1][0][1];
                dp[t][0][2] = dp[t - 1][0][2] + 1;
                for (int w = 1; w <= W; w++) {
                    dp[t][w][1] = Math.max(dp[t - 1][w][1], dp[t - 1][w - 1][2]);
                    dp[t][w][2] = Math.max(dp[t - 1][w - 1][1], dp[t - 1][w][2]) + 1;
                }
            }
        }
        int answer = 0;
        for (int w = 0; w <= W; w++) {
            answer = Math.max(answer, Math.max(dp[T][w][1], dp[T][w][2]));
        }
        System.out.println(answer);
    }
}
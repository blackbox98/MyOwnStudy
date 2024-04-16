import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] coins = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            int M = Integer.parseInt(br.readLine());
            int[] dp = new int[M + 1];
            dp[0] = 1;
            for (int coin : coins) {
                for (int i = coin; i <= M; i++) {
                    if (i - coin > 0) dp[i] += dp[i - coin];
                    else if (i - coin == 0) dp[i]++;
                }
            }
            sb.append(dp[M]).append("\n");
        }
        System.out.println(sb);
    }
}
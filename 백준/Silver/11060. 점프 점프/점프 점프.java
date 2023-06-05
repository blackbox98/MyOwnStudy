import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N];
        final int INF = Integer.MAX_VALUE;
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            if (dp[i] == INF) continue;
            for (int j = 1; j <= nums[i] && i + j < N; j++) {
                dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
            }
        }
        int answer = dp[N - 1] == INF ? -1 : dp[N - 1];
        System.out.println(answer);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] cost = new int[N + 1];
        int[] dp = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            dp[i] = cost[i];
            for (int j = 1; j < i; j++) {
                if (cost[i] > cost[j]) dp[i] = Math.max(dp[i], dp[j] + cost[i]);
            }
        }
        int answer = 0;
        for (int max : dp) {
            answer = Math.max(answer, max);
        }
        System.out.println(answer);
    }
}
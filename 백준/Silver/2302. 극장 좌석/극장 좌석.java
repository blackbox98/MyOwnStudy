import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        int answer = 1;
        int last = 1;
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            int vipNum = Integer.parseInt(br.readLine());
            answer *= dp[vipNum - last];
            last = vipNum + 1;
        }
        answer *= dp[N - last + 1];
        System.out.println(answer);
    }
}
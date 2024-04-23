import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long answer;
        switch (N) {
            case 1:
                answer = 4;
                break;
            case 2:
                answer = 6;
                break;
            case 3:
                answer = 10;
                break;
            default:
                long[] dp = new long[N];
                dp[0] = 1;
                dp[1] = 1;
                for (int i = 2; i < N; i++) {
                    dp[i] = dp[i - 1] + dp[i - 2];
                }
                answer = dp[N - 1] * 3 + dp[N - 2] * 2 + dp[N - 3] * 2 + dp[N - 4];
                break;
        }
        System.out.println(answer);
    }
}
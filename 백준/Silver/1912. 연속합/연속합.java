import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = Integer.parseInt(st.nextToken());
        }
        int answer = dp[0];
        for (int i = 1; i < N; i++) {
            int sum = dp[i - 1] + dp[i];
            if (dp[i] < sum) dp[i] = sum;
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);
    }
}

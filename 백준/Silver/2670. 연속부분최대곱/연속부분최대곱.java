import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        double[] nums = new double[N + 1];
        double[] dp = new double[N + 1];
        double answer = 0;
        for (int i = 1; i <= N; i++) {
            nums[i] = Double.parseDouble(br.readLine());
            dp[i] = Math.max(nums[i], dp[i - 1] * nums[i]);
            answer = Math.max(answer, dp[i]);
        }
        System.out.printf("%.3f\n", answer);
    }
}
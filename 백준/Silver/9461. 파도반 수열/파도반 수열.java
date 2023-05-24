import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] nums = new int[T];
        int max = 0;
        for (int i = 0; i < T; i++) {
            nums[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, nums[i]);
        }
        long[] dp = new long[max + 1];
        if (max > 1) {
            dp[1] = 1;
            dp[2] = 1;
            dp[3] = 1;
            for (int i = 4; i <= max; i++) {
                dp[i] = dp[i - 3] + dp[i - 2];
            }
        } else dp[1] = 1;
        for (int n : nums) {
            System.out.println(dp[n]);
        }
    }
}
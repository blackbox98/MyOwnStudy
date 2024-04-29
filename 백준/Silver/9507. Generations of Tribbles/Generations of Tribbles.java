import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int[] nums = new int[t];
        int max = 0;
        for (int i = 0; i < t; i++) {
            nums[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, nums[i]);
        }
        long[] dp = new long[max + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i <= max; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3] + dp[i - 4];
        }
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(dp[num]).append("\n");
        }
        System.out.println(sb);
    }
}
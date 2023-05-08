import java.util.Arrays;

class Solution {
    public int solution(int x, int y, int n) {
        int[] dp = new int[y + 1];
        Arrays.fill(dp, -1);
        dp[x] = 0;
        for (int idx = x; idx <= y; idx++) {
            if (dp[idx] == -1) continue;
            if (idx + n <= y) {
                if (dp[idx + n] == -1) dp[idx + n] = dp[idx] + 1;
                else dp[idx + n] = Math.min(dp[idx + n], dp[idx] + 1);
            }
            if (idx * 2 <= y) {
                if (dp[idx * 2] == -1) dp[idx * 2] = dp[idx] + 1;
                else dp[idx * 2] = Math.min(dp[idx * 2], dp[idx] + 1);
            }
            if (idx * 3 <= y) {
                if (dp[idx * 3] == -1) dp[idx * 3] = dp[idx] + 1;
                else dp[idx * 3] = Math.min(dp[idx * 3], dp[idx] + 1);
            }
        }
        return dp[y];
    }
}
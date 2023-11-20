class Solution {
    public int solution(String[] strs, String t) {
        int[] dp = new int[t.length() + 1];
        for(int i = 1; i < t.length() + 1; i ++) {
            for (String str : strs) {
                int strLen = str.length();
                if(i - strLen < 0 || !str.equals(t.substring(i - strLen, i))) continue;
                if(i - strLen == 0) {
                    dp[i] = 1;
                    continue;
                }
                if(dp[i - strLen] > 0) {
                    if (dp[i] == 0) dp[i] = dp[i - strLen] + 1;
                    else dp[i] = Math.min(dp[i], dp[i - strLen] + 1);
                }
            }
        }
        return dp[t.length()] == 0 ? -1 : dp[t.length()];
    }
}
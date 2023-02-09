import java.util.Arrays;
class Solution {
    int solution(int[][] land) {
        int answer = 0;
        int[][] memo = new int[land.length][land[0].length];
        memo[0] = Arrays.copyOf(land[0], land[0].length);
        for (int i = 1; i < memo.length; i++) {
            for (int j = 0; j < memo[i].length; j++) {
                for (int k = 0; k < memo[i].length; k++) {
                    if (j == k) continue;
                    memo[i][j] = Math.max(memo[i][j], memo[i - 1][k] + land[i][j]);
                }
            }
        }
        for (int i = 0; i < memo[0].length; i++) {
            answer = answer < memo[memo.length - 1][i] ? memo[memo.length - 1][i] : answer;
        }
        return answer;
    }
}
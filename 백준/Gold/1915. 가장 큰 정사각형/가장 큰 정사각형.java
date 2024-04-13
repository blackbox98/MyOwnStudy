import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n][m];
        for (int r = 0; r < n; r++) {
            char[] tmp = br.readLine().toCharArray();
            for (int c = 0; c < m; c++) {
                dp[r][c] = tmp[c] - '0';
            }
        }
        int maxLen = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (r == 0 || c == 0) maxLen = Math.max(maxLen, dp[r][c]);
                if (r - 1 < 0 || c - 1 < 0 || dp[r][c] == 0) continue;
                if (dp[r - 1][c - 1] > 0 && dp[r - 1][c] > 0 && dp[r][c - 1] > 0) {
                    dp[r][c] = Math.min(dp[r - 1][c - 1], Math.min(dp[r - 1][c], dp[r][c - 1])) + 1;
                    maxLen = Math.max(maxLen, dp[r][c]);
                }
            }
        }
        System.out.println(maxLen * maxLen);
    }
}
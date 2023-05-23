import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[][] testCase = new int[T][2];
        int maxR = 0;
        int maxC = 0;
        for (int tc = 0; tc < T; tc++) {
            testCase[tc][0] = Integer.parseInt(br.readLine());
            testCase[tc][1] = Integer.parseInt(br.readLine());
            maxR = Math.max(maxR, testCase[tc][0]);
            maxC = Math.max(maxC, testCase[tc][1]);
        }
        int[][] dp = new int[maxR + 1][maxC + 1];
        for (int i = 1; i <= maxC; i++) {
            dp[0][i] = i;
        }
        for (int r = 1; r <= maxR; r++) {
            for (int c = 1; c <= maxC; c++) {
                for (int i = 1; i <= c; i++) {
                    dp[r][c] += dp[r - 1][i];
                }
            }
        }
        for (int[] tc : testCase) {
            System.out.println(dp[tc[0]][tc[1]]);
        }
    }
}
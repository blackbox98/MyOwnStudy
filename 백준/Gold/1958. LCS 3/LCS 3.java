import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = new String[3];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = br.readLine();
        }
        System.out.println(LCS(arr));
    }

    private static int LCS(String[] arr) {
        int[][][] dp = new int[arr[0].length() + 1][arr[1].length() + 1][arr[2].length() + 1];
        char c1, c2, c3;
        for (int i = 1; i <= arr[0].length(); i++) {
            c1 = arr[0].charAt(i - 1);
            for (int j = 1; j <= arr[1].length(); j++) {
                c2 = arr[1].charAt(j - 1);
                for (int k = 1; k <= arr[2].length(); k++) {
                    c3 = arr[2].charAt(k - 1);
                    if (c1 == c2 && c2 == c3) dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    else dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
                }
            }
        }
        return dp[arr[0].length()][arr[1].length()][arr[2].length()];
    }
}
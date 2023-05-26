import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int[][] nums = new int[T][2];
        int maxN = 0;
        int maxM = 0;
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            nums[tc][0] = Integer.parseInt(st.nextToken());
            nums[tc][1] = Integer.parseInt(st.nextToken());
            maxN = Math.max(maxN, nums[tc][0]);
            maxM = Math.max(maxM, nums[tc][1]);
        }
        int[][] dp = new int[maxN + 1][maxM + 1];
        for (int i = 1; i <= maxM; i++) {
            dp[1][i] = i;
        }
        for (int i = 2; i <= maxN; i++) {
            for (int j = 2; j <= maxM; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
            }
        }
        for (int[] num : nums) {
            System.out.println(dp[num[0]][num[1]]);
        }
    }
}
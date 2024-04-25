import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] kIdx = K == 0 ? new int[]{N - 1, M - 1} : new int[]{K / M, K % M == 0 ? M - 1 : K % M - 1};
        dp = new int[N][M];
        dp[0][0] = 1;
        moveRobot(0, kIdx[0], 0, kIdx[1], 1);
        if (K == 0) {
            System.out.println(dp[N - 1][M - 1]);
            return;
        }
        moveRobot(kIdx[0], N - 1, kIdx[1], M - 1, dp[kIdx[0]][kIdx[1]]);
        System.out.println(dp[N - 1][M - 1]);
    }

    private static void moveRobot(int sr, int er, int sc, int ec, int num) {
        for (int r = sr + 1; r <= er; r++) {
            dp[r][sc] = num;
        }
        for (int c = sc + 1; c <= ec; c++) {
            dp[sr][c] = num;
        }
        for (int r = sr + 1; r <= er; r++) {
            for (int c = sc + 1; c <= ec; c++) {
                dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
            }
        }
    }
}
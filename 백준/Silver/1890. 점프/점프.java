import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        long[][] dp = new long[N][N];
        dp[0][0] = 1;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int jump = map[r][c];
                if (jump == 0) break;
                if (r + jump < N) dp[r + jump][c] += dp[r][c];
                if (c + jump < N) dp[r][c + jump] += dp[r][c];
            }
        }
        System.out.println(dp[N - 1][N - 1]);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][3];
        StringTokenizer st;
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 3; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int[][][] dp = new int[N][3][2];
        for (int c = 0; c < 3; c++) {
            dp[0][c] = new int[]{map[0][c], map[0][c]};
        }
        int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}};
        for (int r = 1; r < N; r++) {
            for (int c = 0; c < 3; c++) {
                dp[r][c] = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};
                for (int[] d : dir) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= 3) continue;
                    dp[r][c][0] = Math.max(dp[r][c][0], map[r][c] + dp[nr][nc][0]);
                    dp[r][c][1] = Math.min(dp[r][c][1], map[r][c] + dp[nr][nc][1]);
                }
            }
        }
        int[] answer = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};
        for (int c = 0; c < 3; c++) {
            answer[0] = Math.max(answer[0], dp[N - 1][c][0]);
            answer[1] = Math.min(answer[1], dp[N - 1][c][1]);
        }
        System.out.println(answer[0] + " " + answer[1]);
    }
}
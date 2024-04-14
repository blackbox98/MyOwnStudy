import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map, dp;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][n];
        StringTokenizer st;
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (dp[r][c] > 1) continue;
                answer = Math.max(answer, dfs(r, c));
            }
        }
        System.out.println(answer);
    }

    private static int dfs(int r, int c) {
        if (dp[r][c] != 0) return dp[r][c];
        dp[r][c] = 1;
        for (int[] d : dir) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (check(nr, nc) || map[nr][nc] <= map[r][c]) continue;
            dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
        }
        return dp[r][c];
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= n;
    }
}
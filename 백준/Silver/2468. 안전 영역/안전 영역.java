import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static int N, answer = 1, high = 0;
    static int[][] map;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                high = Math.max(high, map[r][c]);
            }
        }

        while (high > 0) {
            v = new boolean[N][N];
            int cnt = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] > high && !v[r][c]) {
                        v[r][c] = true;
                        dfs(r, c);
                        cnt++;
                    }
                }
            }
            answer = Math.max(answer, cnt);
            high--;
        }
        System.out.println(answer);
    }

    private static void dfs(int r, int c) {
        for (int[] d : dir) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc] && map[nr][nc] > high) {
                v[nr][nc] = true;
                dfs(nr, nc);
            }
        }
    }
}
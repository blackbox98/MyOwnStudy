import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean answer;
    static int N, M;
    static char[][] map;
    static boolean[][] v;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
        }
        v = new boolean[N][M];
        answer = false;
        L:
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (v[r][c]) continue;
                dfs(map[r][c], r, c, -3);
                if (answer) break L;
            }
        }
        System.out.println(answer ? "Yes" : "No");
    }

    private static void dfs(char ch, int r, int c, int curD) {
        v[r][c] = true;
        for (int i = 0; i < 4; i++) {
            if (i == (curD + 2) % 4) continue;
            int nr = r + dir[i][0];
            int nc = c + dir[i][1];
            if (check(nr, nc)) continue;
            if (v[nr][nc]) {
                if (map[nr][nc] == ch) {
                    answer = true;
                    return;
                }
                continue;
            }
            if (map[nr][nc] == ch) dfs(ch, nr, nc, i);
        }
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}
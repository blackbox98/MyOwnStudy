import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, answer;
    static char[][] map;
    static int[][] v;
    static int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 0}, {1, -1}, {0, -1}};
    /*
      1 2
    6 X 3
    5 4
    */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        v = new int[N][N];
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
            for (int c = 0; c < N; c++) {
                v[r][c] = -1;
            }
        }
        answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] != 'X' || v[r][c] != -1) continue;
                dfs(r, c, 0);
            }
        }
        System.out.println(answer);
    }

    private static void dfs(int curR, int curC, int color) {
        v[curR][curC] = color;
        answer = Math.max(answer, 1);
        for (int[] d : dir) {
            int nr = curR + d[0];
            int nc = curC + d[1];
            if (check(nr, nc) || map[nr][nc] != 'X') continue;
            if (v[nr][nc] == -1) dfs(nr, nc, 1 - color);
            answer = Math.max(answer, v[nr][nc] == color ? 3 : 2);
        }
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}
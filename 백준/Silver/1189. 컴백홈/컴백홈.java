import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, K, answer;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        }
        boolean[][] v = new boolean[R][C];
        v[R - 1][0] = true;
        answer = 0;
        dfs(v, R - 1, 0, 1);
        System.out.println(answer);
    }

    private static void dfs(boolean[][] v, int r, int c, int dist) {
        if (r == 0 && c == C - 1) {
            if (dist == K) answer++;
            return;
        }
        for (int[] d : dir) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (check(nr, nc) || v[nr][nc] || map[nr][nc] == 'T') continue;
            v[nr][nc] = true;
            dfs(v, nr, nc, dist + 1);
            v[nr][nc] = false;
        }
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }
}
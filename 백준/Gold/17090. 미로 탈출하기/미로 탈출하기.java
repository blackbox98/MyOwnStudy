import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static int[][] map;
    static int[][] dir = {{0, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // . R D L U
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 2][M + 2];
        for (int r = 1; r <= N; r++) {
            char[] tmp = br.readLine().toCharArray();
            for (int c = 0; c < M; c++) {
                int d = 1;
                switch (tmp[c]) {
                    case 'U':
                        d = 2;
                        break;
                    case 'R':
                        d = 3;
                        break;
                    case 'D':
                        d = 4;
                        break;
                    default:
                        break;
                }
                map[r][c + 1] = d;
            }
        }
        answer = 0;
        v = new boolean[N + 2][M + 2];
        for (int r = 1; r <= N; r++) {
            dfs(r, 0);
            dfs(r, M + 1);
        }
        for (int c = 1; c <= M; c++) {
            dfs(0, c);
            dfs(N + 1, c);
        }
        System.out.println(answer);
    }

    private static void dfs(int r, int c) {
        if (map[r][c] != 0) answer++;
        for (int d = 1; d < 5; d++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];
            if (check(nr, nc) || v[nr][nc] || map[nr][nc] != d) continue;
            v[nr][nc] = true;
            dfs(nr, nc);
        }
    }

    private static boolean check(int r, int c) {
        return r < 1 || r > N || c < 1 || c > M;
    }
}
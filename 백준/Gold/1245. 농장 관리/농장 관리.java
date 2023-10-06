import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] v;
    static int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    /*
    0 1 2
    3 x 4
    5 6 7
    */
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        v = new boolean[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (!v[r][c] && dfs(r, c, map[r][c])) answer++;
            }
        }
        System.out.println(answer);
    }
    
    private static boolean dfs(int sr, int sc, int sh) {
        boolean flag = true;
        for (int[] d : dir) {
            int nr = sr + d[0];
            int nc = sc + d[1];
            if (check(nr, nc)) continue;
            if (map[nr][nc] != sh) {
                if (map[nr][nc] > sh) flag = false;
                continue;
            }
            if (v[nr][nc]) continue;
            v[nr][nc] = true;
            if (!dfs(nr, nc, map[nr][nc])) flag = false;
        }
        return flag;
    }
    
    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}
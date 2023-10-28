import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken()) - 1;
        int sc = Integer.parseInt(st.nextToken()) - 1;
        st = new StringTokenizer(br.readLine());
        int er = Integer.parseInt(st.nextToken()) - 1;
        int ec = Integer.parseInt(st.nextToken()) - 1;
        for (int r = 0; r < N; r++) {
            String[] tmp = br.readLine().split(" ");
            for (int c = 0; c < M; c++) {
                map[r][c] = tmp[c].charAt(0);
            }
        }
        System.out.println(bfs(sr, sc, er, ec));
    }

    private static int bfs(int sr, int sc, int er, int ec) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc, 0});
        int[][][] v = new int[N][M][2];
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            int pd = v[p[0]][p[1]][p[2]];
            if (p[0] == er && p[1] == ec) return pd;
            for (int[] d : dir) {
                int nr = p[0] + d[0];
                int nc = p[1] + d[1];
                if (check(nr, nc)) continue;
                if (map[nr][nc] == '0' && (v[nr][nc][p[2]] == 0 || v[nr][nc][p[2]] > pd + 1)) {
                    queue.offer(new int[]{nr, nc, p[2]});
                    v[nr][nc][p[2]] = pd + 1;
                }
                if (map[nr][nc] == '1' && p[2] < 1 && (v[nr][nc][p[2] + 1] == 0 || v[nr][nc][p[2] + 1] > pd + 1)) {
                    queue.offer(new int[]{nr, nc, p[2] + 1});
                    v[nr][nc][p[2] + 1] = pd + 1;
                }
            }
        }
        return -1;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}
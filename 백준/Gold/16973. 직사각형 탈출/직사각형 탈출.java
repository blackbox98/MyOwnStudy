import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, H, W;
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int sr = Integer.parseInt(st.nextToken()) - 1;
        int sc = Integer.parseInt(st.nextToken()) - 1;
        int fr = Integer.parseInt(st.nextToken()) - 1;
        int fc = Integer.parseInt(st.nextToken()) - 1;
        System.out.println(bfs(sr, sc, fr, fc));
    }

    private static int bfs(int sr, int sc, int fr, int fc) {
        Point start = new Point(sr, sc, 0);
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        boolean[][] v = new boolean[N][M];
        v[sr][sc] = true;
        int nr, nc;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (p.r == fr && p.c == fc) return p.move;
            for (int[] d : dir) {
                nr = p.r + d[0];
                nc = p.c + d[1];
                if (checkRange(nr, nc) || v[nr][nc] || checkRectangle(nr, nc)) continue;
                queue.offer(new Point(nr, nc, p.move + 1));
                v[nr][nc] = true;
            }
        }
        return -1;
    }

    private static boolean checkRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static boolean checkRectangle(int nr, int nc) {
        for (int r = nr; r < nr + H; r++) {
            if (checkRange(r, nc) || map[r][nc] == 1) return true;
            if (checkRange(r, nc + W - 1) || map[r][nc + W - 1] == 1) return true;
        }
        for (int c = nc; c < nc + W; c++) {
            if (checkRange(nr, c) || map[nr][c] == 1) return true;
            if (checkRange(nr + H - 1, c) || map[nr + H - 1][c] == 1) return true;
        }
        return false;
    }

    private static class Point {
        int r;
        int c;
        int move;

        private Point(int r, int c, int move) {
            this.r = r;
            this.c = c;
            this.move = move;
        }
    }
}
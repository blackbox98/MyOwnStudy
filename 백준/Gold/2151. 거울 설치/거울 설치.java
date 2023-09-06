import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        Point[] door = new Point[2];
        int idx = 0;
        char[] tmp;
        for (int r = 0; r < N; r++) {
            tmp = br.readLine().toCharArray();
            for (int c = 0; c < N; c++) {
                map[r][c] = tmp[c];
                if (map[r][c] == '#') door[idx++] = new Point(r, c, -1, 0);
            }
        }
        System.out.println(bfs(door));
    }

    private static int bfs(Point[] door) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean[][][] v = new boolean[N][N][dir.length];
        int sr = door[0].r;
        int sc = door[0].c;
        for (int d = 0; d < dir.length; d++) {
            pq.offer(new Point(sr, sc, d, 0));
        }
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            int curR = p.r;
            int curC = p.c;
            int curD = p.d;
            int curCnt = p.cnt;
            if (curR == door[1].r && curC == door[1].c) return curCnt;
            v[curR][curC][curD] = true;
            int nr = curR + dir[curD][0];
            int nc = curC + dir[curD][1];
            if (nr < 0 | nr >= N || nc < 0 || nc >= N || map[nr][nc] == '*' || v[nr][nc][curD]) continue;
            if (map[nr][nc] == '!') {
                pq.offer(new Point(nr, nc, (curD + 1) % dir.length, curCnt + 1));
                pq.offer(new Point(nr, nc, (curD + 3) % dir.length, curCnt + 1));
            }
            pq.offer(new Point(nr, nc, curD, curCnt));
        }
        return 0;
    }

    private static class Point implements Comparable<Point> {
        int r;
        int c;
        int d;
        int cnt;

        private Point(int r, int c, int d, int cnt) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            return this.cnt - o.cnt;
        }
    }
}
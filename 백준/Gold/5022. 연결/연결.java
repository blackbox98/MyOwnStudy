import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Point[][] map;
    static boolean[][] v;
    static Queue<Point> queue;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()) + 1;
        M = Integer.parseInt(st.nextToken()) + 1;
        Point[] points = new Point[4];
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
        }
        int dist1 = getDist(points[0], points[1], points[2], points[3]);
        int dist2 = getDist(points[2], points[3], points[0], points[1]);
        if (dist1 == Integer.MAX_VALUE && dist2 == Integer.MAX_VALUE) System.out.println("IMPOSSIBLE");
        else System.out.println(Math.min(dist1, dist2));
    }

    private static int getDist(Point start1, Point start2, Point end1, Point end2) {
        map = new Point[N][M];
        map[start1.r][start1.c] = start1;
        v = new boolean[N][M];
        v[start1.r][start1.c] = true;
        v[end1.r][end1.c] = true;
        v[end2.r][end2.c] = true;

        int startDist = bfs(start1, start2);

        v = new boolean[N][M];
        v[start1.r][start1.c] = true;
        v[start2.r][start2.c] = true;
        Point next = map[start2.r][start2.c];
        while (true) {
            v[next.r][next.c] = true;
            next = map[next.r][next.c];
            if (next.r == start1.r && next.c == start1.c) {
                break;
            }
        }

        v[end1.r][end1.c] = true;

        int endDist = bfs(end1, end2);

        if (startDist == 0 || endDist == 0) return Integer.MAX_VALUE;
        else return startDist + endDist;
    }

    private static int bfs(Point start1, Point start2) {
        queue = new LinkedList<>();
        queue.offer(start1);
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int pr = p.r;
            int pc = p.c;
            if (pr == start2.r && pc == start2.c) {
                return p.dist;
            }
            int nd = p.dist + 1;
            for (int[] d : dir) {
                int nr = pr + d[0];
                int nc = pc + d[1];
                if (check(nr, nc) || v[nr][nc]) continue;
                queue.offer(new Point(nr, nc, nd));
                v[nr][nc] = true;
                map[nr][nc] = p;
            }
        }
        return 0;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static class Point {
        int r;
        int c;
        int dist;

        private Point(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
}
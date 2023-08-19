import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int W, H;
    static char[][] map;
    static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        Point[] pointC = new Point[2];
        int idx = 0;
        for (int r = 0; r < H; r++) {
            String s = br.readLine();
            for (int c = 0; c < W; c++) {
                map[r][c] = s.charAt(c);
                if (map[r][c] == 'C') {
                    pointC[idx++] = new Point(r, c, -5, -1);
                }
            }
        }
        System.out.println(bfs(pointC[0], pointC[1]));
    }

    private static int bfs(Point start, Point end) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(start);
        int[][][] v = new int[direction.length][H][W];
        for (int i = 0; i < direction.length; i++) {
            for (int j = 0; j < H; j++) {
                Arrays.fill(v[i][j], Integer.MAX_VALUE);
            }
        }
        int min = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            if (p.r == end.r && p.c == end.c) {
                min = Math.min(min, p.mirror);
                continue;
            }
            for (int i = 0; i < direction.length; i++) {
                int nr = p.r + direction[i][0];
                int nc = p.c + direction[i][1];
                int nm = p.mirror;
                if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] != '*' && Math.abs(p.dir - i) != 2) {
                    if (p.dir != i) nm++;
                    if (v[i][nr][nc] > nm) {
                        pq.offer(new Point(nr, nc, i, nm));
                        v[i][nr][nc] = nm;
                    }
                }
            }
        }
        return min;
    }

    static class Point implements Comparable<Point> {
        int r, c, dir, mirror;

        public Point(int r, int c, int dir, int mirror) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.mirror = mirror;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.mirror, o.mirror);
        }
    }
}
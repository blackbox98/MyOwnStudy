import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, F;
    static Point taxi;
    static int[][] map;
    static int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; // 상 좌 하 우
    static Queue<int[]> queue;
    static boolean[][] v;

    private static class Point implements Comparable<Point> {
        int r;
        int c;
        int dist;

        private Point(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        @Override
        public int compareTo(Point o) {
            if (this.dist == o.dist) {
                if (this.r == o.r) return this.c - o.c;
                else return this.r - o.r;
            } else return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken()) * -1;
            }
        }
        st = new StringTokenizer(br.readLine());
        taxi = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);
        Map<Integer, Point> passengerMap = new HashMap<>();
        for (int num = 1; num <= M; num++) {
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken()) - 1;
            int sc = Integer.parseInt(st.nextToken()) - 1;
            int er = Integer.parseInt(st.nextToken()) - 1;
            int ec = Integer.parseInt(st.nextToken()) - 1;
            passengerMap.put(num, new Point(er, ec, 0));
            map[sr][sc] = num;
        }
        int next = -1;
        for (int i = 0; F >= 0 && i < M; i++) {
            next = findPassenger();
            if (next == -1 || F == -1) break;
            goToDestination(passengerMap.get(next));
        }
        System.out.println((F < 0 || next == -1) ? -1 : F);
    }

    private static int findPassenger() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        queue = new LinkedList<>();
        queue.offer(new int[]{taxi.r, taxi.c, 0});
        v = new boolean[N][N];
        v[taxi.r][taxi.c] = true;
        int minDist = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cr = cur[0];
            int cc = cur[1];
            if (map[cr][cc] > 0 && minDist >= cur[2]) {
                minDist = cur[2];
                pq.offer(new Point(cr, cc, cur[2]));
                continue;
            }
            for (int[] d : dir) {
                int nr = cr + d[0];
                int nc = cc + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] == -1) continue;
                queue.offer(new int[]{nr, nc, cur[2] + 1});
                v[nr][nc] = true;
            }
        }
        if (pq.isEmpty()) return -1;
        taxi = pq.poll();
        F -= taxi.dist;
        if (F < 0) return -1;
        int nextNum = map[taxi.r][taxi.c];
        map[taxi.r][taxi.c] = 0;
        return nextNum;
    }

    private static void goToDestination(Point point) {
        int dist = 0;
        queue = new LinkedList<>();
        queue.offer(new int[]{taxi.r, taxi.c, 0});
        v = new boolean[N][N];
        v[taxi.r][taxi.c] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cr = cur[0];
            int cc = cur[1];
            if (cr == point.r && cc == point.c) {
                dist = cur[2];
                taxi = new Point(cr, cc, 0);
                break;
            }
            for (int[] d : dir) {
                int nr = cr + d[0];
                int nc = cc + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] == -1) continue;
                queue.offer(new int[]{nr, nc, cur[2] + 1});
                v[nr][nc] = true;
            }
        }
        if (dist == 0) {
            F = -1;
            return;
        }
        F -= dist;
        if (F < 0) return;
        F += dist * 2;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}
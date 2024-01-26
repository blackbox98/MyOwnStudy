import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int h, w;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    private static class Prisoner implements Comparable<Prisoner> {
        int r;
        int c;
        int doorCnt;

        private Prisoner(int r, int c) {
            this.r = r;
            this.c = c;
            this.doorCnt = 0;
        }

        private Prisoner(int r, int c, int doorCnt) {
            this.r = r;
            this.c = c;
            this.doorCnt = doorCnt;
        }

        @Override
        public int compareTo(Prisoner o) {
            return this.doorCnt - o.doorCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h + 2][w + 2];
            Prisoner[] prisoners = new Prisoner[3];
            prisoners[0] = new Prisoner(0, 0);
            int idx = 1;
            for (int r = 1; r <= h; r++) {
                char[] tmp = br.readLine().toCharArray();
                for (int c = 1; c <= w; c++) {
                    map[r][c] = tmp[c - 1];
                    if (map[r][c] == '$') prisoners[idx++] = new Prisoner(r, c);
                }
            }
            int[][][] maps = new int[3][h + 2][w + 2];
            for (int i = 0; i < 3; i++) {
                maps[i] = bfs(prisoners[i]);
            }
            sb.append(getMinDoor(maps)).append("\n");
        }
        System.out.println(sb);
    }

    private static int[][] bfs(Prisoner p) {
        int[][] curMap = new int[h + 2][w + 2];
        for (int r = 0; r < h + 2; r++) {
            Arrays.fill(curMap[r], -1);
        }
        PriorityQueue<Prisoner> pq = new PriorityQueue<>();
        pq.offer(new Prisoner(p.r, p.c, 0));
        boolean[][] v = new boolean[h + 2][w + 2];
        v[p.r][p.c] = true;
        while (!pq.isEmpty()) {
            Prisoner cur = pq.poll();
            curMap[cur.r][cur.c] = cur.doorCnt;
            for (int[] d : dir) {
                int nr = cur.r + d[0];
                int nc = cur.c + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] == '*') continue;
                pq.offer(new Prisoner(nr, nc, map[nr][nc] == '#' ? cur.doorCnt + 1 : cur.doorCnt));
                v[nr][nc] = true;
            }
        }
        return curMap;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= h + 2 || c < 0 || c >= w + 2;
    }

    private static int getMinDoor(int[][][] maps) {
        int min = Integer.MAX_VALUE;
        for (int r = 1; r <= h; r++) {
            for (int c = 1; c <= w; c++) {
                if (map[r][c] == '*' || !(maps[0][r][c] != -1 && maps[1][r][c] != -1 && maps[2][r][c] != -1)) continue;
                int sum = maps[0][r][c] + maps[1][r][c] + maps[2][r][c];
                if (map[r][c] == '#') sum -= 2;
                if (min > sum) min = sum;
            }
        }
        return min;
    }
}
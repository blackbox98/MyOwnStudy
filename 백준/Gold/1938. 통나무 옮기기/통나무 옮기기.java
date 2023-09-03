import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static char[][] map;
    static boolean[][][] v;
    static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        char[] tmp;
        Log B = new Log();
        Log E = new Log();
        int BIdx = 1;
        int EIdx = 1;
        for (int r = 0; r < N; r++) {
            tmp = br.readLine().toCharArray();
            for (int c = 0; c < N; c++) {
                map[r][c] = tmp[c];
                if (map[r][c] == 'B') {
                    if (BIdx == 2) {
                        B.r = r;
                        B.c = c;
                    } else if (BIdx == 3 && B.r != r) B.d = 1;
                    map[r][c] = '0';
                    BIdx++;
                } else if (map[r][c] == 'E') {
                    if (EIdx == 2) {
                        E.r = r;
                        E.c = c;
                    } else if (EIdx == 3 && E.r != r) E.d = 1;
                    map[r][c] = '0';
                    EIdx++;
                }
            }
        }
        System.out.println(bfs(B, E));
    }

    private static int bfs(Log start, Log end) {
        Queue<Log> queue = new LinkedList<>();
        queue.offer(start);
        v = new boolean[2][N][N];
        v[start.d][start.r][start.c] = true;
        while (!queue.isEmpty()) {
            Log B = queue.poll();
            if (B.r == end.r && B.c == end.c && B.d == end.d) return B.cnt;
            // 상하좌우
            for (int[] dir : direction) {
                int nr = B.r + dir[0];
                int nc = B.c + dir[1];
                Log nB = new Log(nr, nc, B.d, B.cnt + 1);
                if (check(nB)) {
                    queue.offer(nB);
                    v[B.d][nr][nc] = true;
                }
            }
            // 회전
            if (checkArea(B.r, B.c)) {
                Log nB = new Log(B.r, B.c, (B.d + 1) % 2, B.cnt + 1);
                if (check(nB)) {
                    queue.offer(nB);
                    v[B.d][B.r][B.c] = true;
                }
            }
        }
        return 0;
    }

    private static boolean check(Log B) {
        if (B.d == 0) {
            return B.r >= 0 && B.r < N
                    && B.c - 1 >= 0 && B.c + 1 < N
                    && map[B.r][B.c - 1] != '1'
                    && map[B.r][B.c] != '1'
                    && map[B.r][B.c + 1] != '1'
                    && !v[B.d][B.r][B.c];
        } else return B.c >= 0 && B.c < N
                && B.r - 1 >= 0 && B.r + 1 < N
                && map[B.r - 1][B.c] != '1'
                && map[B.r][B.c] != '1'
                && map[B.r + 1][B.c] != '1'
                && !v[B.d][B.r][B.c];
    }

    private static boolean checkArea(int sr, int sc) {
        for (int r = sr - 1; r <= sr + 1; r++) {
            for (int c = sc - 1; c <= sc + 1; c++) {
                if (r < 0 || r >= N || c < 0 || c >= N || map[r][c] == '1') return false;
            }
        }
        return true;
    }

    private static class Log {
        int r;
        int c;
        int d;
        int cnt;

        private Log() {
            this.r = 0;
            this.c = 0;
            this.d = 0;
            this.cnt = 0;
        }

        private Log(int r, int c, int d, int cnt) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.cnt = cnt;
        }
    }
}
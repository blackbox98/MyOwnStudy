import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] map;
    static Queue<int[]> queue;
    static boolean[][] v;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        }
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        boolean turn = true;
        for (int i = 0; i < N; i++) {
            throwStick(R - Integer.parseInt(st.nextToken()), turn);
            turn = !turn;
        }
        printMap();
    }

    private static void throwStick(int h, boolean turn) {
        if (turn) {
            for (int c = 0; c < C; c++) {
                if (map[h][c] == '.') continue;
                map[h][c] = '.';
                break;
            }
        } else {
            for (int c = C - 1; c >= 0; c--) {
                if (map[h][c] == '.') continue;
                map[h][c] = '.';
                break;
            }
        }
        queue = new LinkedList<>();
        v = new boolean[R][C];
        int nr, nc;
        for (int c = 0; c < C; c++) {
            if (v[R - 1][c] || map[R - 1][c] == '.') continue;
            queue.offer(new int[]{R - 1, c});
            v[R - 1][c] = true;
            while (!queue.isEmpty()) {
                int[] point = queue.poll();
                for (int[] d : dir) {
                    nr = point[0] + d[0];
                    nc = point[1] + d[1];
                    if (!check(nr, nc) || v[nr][nc] || map[nr][nc] == '.') continue;
                    queue.offer(new int[]{nr, nc});
                    v[nr][nc] = true;
                }
            }
        }
        int dif = 100;
        int cnt, lastX;
        for (int c = 0; c < C; c++) {
            cnt = 0;
            lastX = 0;
            for (int r = R - 1; r >= 0; r--) {
                if (!v[r][c] && map[r][c] == 'x') {
                    if (lastX == 1) {
                        cnt = 0;
                        continue;
                    }
                    if (cnt < R) dif = Math.min(dif, cnt);
                    if (dif == 0) return;
                    lastX = 1;
                    cnt = 0;
                } else if (v[r][c]) {
                    lastX = -1;
                    cnt = 0;
                } else cnt++;
            }
        }
        if (dif == 100) return;
        for (int r = R - 1; r >= 0; r--) {
            for (int c = 0; c < C; c++) {
                if (v[r][c] || map[r][c] == '.') continue;
                map[r][c] = '.';
                map[r + dif][c] = 'x';
            }
        }
    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static void printMap() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                sb.append(map[r][c]);
            }
            if (r < R - 1) sb.append("\n");
        }
        System.out.println(sb);
    }
}
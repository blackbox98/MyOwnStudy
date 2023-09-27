import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] map;
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
        int turn = 1;
        int stickH;
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            stickH = Integer.parseInt(st.nextToken());
            if (turn == 1) {
                for (int c = 0; c < C; c++) {
                    if (map[R - stickH][c] == '.') continue;
                    map[R - stickH][c] = '.';
                    break;
                }
            } else {
                for (int c = C - 1; c >= 0; c--) {
                    if (map[R - stickH][c] == '.') continue;
                    map[R - stickH][c] = '.';
                    break;
                }
            }
            v = new boolean[R][C];
            for (int c = 0; c < C; c++) {
                if (map[R - 1][c] == '.') continue;
                bfs(R - 1, c);
            }
            L:
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (v[r][c] || map[r][c] == '.') continue;
                    fallDown(r, c);
                    break L;
                }
            }
            turn *= -1;
        }
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                sb.append(map[r][c]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs(int sr, int sc) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        v[sr][sc] = true;
        int[] point;
        int nr, nc;
        while (!queue.isEmpty()) {
            point = queue.poll();
            for (int[] d : dir) {
                nr = point[0] + d[0];
                nc = point[1] + d[1];
                if (check(nr, nc) || v[nr][nc]) continue;
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
            }
        }
    }

    private static void fallDown(int sr, int sc) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        boolean[][] visit = new boolean[R][C];
        visit[sr][sc] = true;
        int[] point;
        int pr, pc, nr, nc;
        int fallH = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            point = queue.poll();
            pr = point[0];
            pc = point[1];
            if (fallH > 0 && pr + 1 < R && map[pr + 1][pc] == '.') {
                int cnt = 0;
                for (int r = pr + 1; r < R; r++) {
                    if (map[r][pc] == '.') {
                        cnt++;
                        if (r == R - 1) fallH = Math.min(fallH, cnt);
                    } else if (!v[r][pc] && map[r][pc] == 'x') break;
                    else {
                        fallH = Math.min(fallH, cnt);
                        break;
                    }
                }
            }
            for (int[] d : dir) {
                nr = pr + d[0];
                nc = pc + d[1];
                if (check(nr, nc) || visit[nr][nc]) continue;
                queue.offer(new int[]{nr, nc});
                visit[nr][nc] = true;
            }
        }
        if (fallH == Integer.MAX_VALUE || fallH == 0) return;
        for (int r = R - 1; r >= 0; r--) {
            if (r + fallH >= R) continue;
            for (int c = 0; c < C; c++) {
                if (!visit[r][c]) continue;
                map[r + fallH][c] = 'x';
                map[r][c] = '.';
            }
        }
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C || map[r][c] == '.';
    }
}
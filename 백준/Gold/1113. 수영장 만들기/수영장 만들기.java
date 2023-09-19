import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static Queue<Land> queue;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        v = new boolean[N][M];
        boolean[] check = new boolean[10];
        char[] tmp;
        for (int r = 0; r < N; r++) {
            tmp = br.readLine().toCharArray();
            for (int c = 0; c < M; c++) {
                map[r][c] = tmp[c] - '0';
                check[map[r][c]] = true;
            }
        }
        int answer = 0;
        for (int i = 0; i < 10; i++) {
            if (!check[i]) continue;
            for (int r = 0; r < N; r++) {
                if (r == 0 || r == N - 1) continue;
                for (int c = 0; c < M; c++) {
                    if (c == 0 || c == M - 1 || map[r][c] != i) continue;
                    answer += bfs(r, c, map[r][c]);
                }
            }
        }
        System.out.println(answer);
    }

    private static int bfs(int sr, int sc, int sh) {
        int result = 0;
        queue = new LinkedList<>();
        queue.offer(new Land(sr, sc, sh));
        v = new boolean[N][M];
        v[sr][sc] = true;
        int nr, nc, nh, minH = 9;
        while (!queue.isEmpty()) {
            Land land = queue.poll();
            for (int[] d : dir) {
                nr = land.r + d[0];
                nc = land.c + d[1];
                nh = map[nr][nc];
                if (check(nr, nc)) {
                    if (map[nr][nc] < land.h) return 0;
                    minH = Math.min(minH, nh);
                    continue;
                }
                if (v[nr][nc]) continue;
                if (land.h < nh) {
                    minH = Math.min(minH, nh);
                    continue;
                } else if (land.h > nh) return 0;
                queue.offer(new Land(nr, nc, nh));
                v[nr][nc] = true;
            }
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (!v[r][c]) continue;
                result += minH - map[r][c];
                map[r][c] = minH;
            }
        }
        return result;
    }

    private static boolean check(int r, int c) {
        return r == 0 || r == N - 1 || c == 0 || c == M - 1;
    }

    private static class Land {
        int r;
        int c;
        int h;

        private Land(int r, int c, int h) {
            this.r = r;
            this.c = c;
            this.h = h;
        }
    }
}
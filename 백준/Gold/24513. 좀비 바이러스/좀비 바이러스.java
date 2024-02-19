import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] answer;
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static Queue<int[]> queue;
    static int[][][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        queue = new LinkedList<>();
        v = new int[N][M][3];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                for (int i = 0; i < 3; i++) {
                    v[r][c][i] = Integer.MAX_VALUE;
                }
                if (map[r][c] > 0) {
                    queue.offer(new int[]{r, c, map[r][c], 0});
                    v[r][c][map[r][c]] = 0;
                }
            }
        }
        answer = new int[4];
        bfs();
        System.out.printf("%d %d %d\n", answer[1], answer[2], answer[3]);
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int v1 = v[cur[0]][cur[1]][1] == Integer.MAX_VALUE ? 0 : 1;
            int v2 = v[cur[0]][cur[1]][2] == Integer.MAX_VALUE ? 0 : 2;
            map[cur[0]][cur[1]] = v1 + v2;
            answer[map[cur[0]][cur[1]]]++;
            if (map[cur[0]][cur[1]] == 3) continue;
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || map[nr][nc] != 0 || v[nr][nc][cur[2]] <= cur[3] + 1 || v[nr][nc][3 - cur[2]] <= cur[3])
                    continue;
                if (v[nr][nc][3 - cur[2]] == Integer.MAX_VALUE) queue.offer(new int[]{nr, nc, cur[2], cur[3] + 1});
                v[nr][nc][cur[2]] = cur[3] + 1;
            }
        }
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}
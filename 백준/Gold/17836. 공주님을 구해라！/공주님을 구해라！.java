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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = bfs();
        System.out.println(answer > T ? "Fail" : answer);
    }

    private static int bfs() {
        int dist = Integer.MAX_VALUE;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0});
        boolean[][] v = new boolean[N][M];
        v[0][0] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[2] >= dist) break;
            if (cur[0] == N - 1 && cur[1] == M - 1) {
                dist = cur[2];
                break;
            } else if (map[cur[0]][cur[1]] == 2) {
                dist = Math.min(dist, cur[2] + (N - 1 - cur[0]) + (M - 1 - cur[1]));
            }
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] == 1) continue;
                queue.offer(new int[]{nr, nc, cur[2] + 1});
                v[nr][nc] = true;
            }
        }
        return dist;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}
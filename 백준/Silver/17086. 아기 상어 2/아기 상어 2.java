import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map, distMap;
    static int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
    /*
    0 1 2
    7 x 3
    6 5 4
    */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        distMap = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 0) continue;
                bfs(r, c);
            }
        }
        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (distMap[r][c] == 0) continue;
                answer = Math.max(answer, distMap[r][c]);
            }
        }
        System.out.println(answer);
    }

    private static void bfs(int sr, int sc) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc, 0});
        boolean[][] v = new boolean[N][M];
        v[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (distMap[cur[0]][cur[1]] == 0 || distMap[cur[0]][cur[1]] > cur[2]) {
                distMap[cur[0]][cur[1]] = cur[2];
            } else continue;
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] == 1) continue;
                queue.offer(new int[]{nr, nc, cur[2] + 1});
                v[nr][nc] = true;
            }
        }
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, L, R;
    static int[] start;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        start = new int[2];
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
            for (int c = 0; c < M; c++) {
                if (map[r][c] == '2') start = new int[]{r, c};
            }
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        int area = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1], 0, 0});
        int[][] v = new int[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                v[r][c] = Integer.MAX_VALUE;
            }
        }
        v[start[0]][start[1]] = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || map[nr][nc] == '1') continue;
                int[] next = new int[]{nr, nc, cur[2], cur[3]};
                if (d[1] == -1) next[2] += 1;
                else if (d[1] == 1) next[3] += 1;
                if (next[2] > L || next[3] > R || (v[nr][nc] != Integer.MAX_VALUE && v[nr][nc] <= next[2] + next[3]))
                    continue;
                queue.offer(next);
                if (v[nr][nc] == Integer.MAX_VALUE) area++;
                v[nr][nc] = next[2] + next[3];
            }
        }
        return area;
    }

    private static boolean check(int nr, int nc) {
        return nr < 0 || nr >= N || nc < 0 || nc >= M;
    }
}
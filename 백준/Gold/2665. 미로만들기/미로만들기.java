import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static final int INF = 987654321;
    static int N;
    static char[][] map;
    static int[][] dist;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        dist = new int[N][N];
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
            Arrays.fill(dist[r], INF);
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        dist[0][0] = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || dist[nr][nc] <= dist[r][c]) continue;
                dist[nr][nc] = dist[r][c];
                if (map[nr][nc] == '0') dist[nr][nc]++;
                queue.offer(new int[]{nr, nc});
            }
        }
        return dist[N - 1][N - 1];
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static Queue<int[]> queue;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[r][c]);
                max = Math.max(max, map[r][c]);
            }
        }
        int answer = Integer.MAX_VALUE;
        int left = 0;
        int right = max - min;
        L:
        while (left <= right) {
            int mid = (left + right) / 2;
            for (int i = min; i <= max; i++) {
                if (map[0][0] < i || map[0][0] > i + mid || !bfs(i, i + mid)) continue;
                answer = Math.min(answer, mid);
                right = mid - 1;
                continue L;
            }
            left = mid + 1;
        }
        System.out.println(answer);
    }

    private static boolean bfs(int min, int max) {
        queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        v = new boolean[n][n];
        v[0][0] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == n - 1 && cur[1] == n - 1) return true;
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] < min || map[nr][nc] > max) continue;
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
            }
        }
        return false;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= n;
    }
}
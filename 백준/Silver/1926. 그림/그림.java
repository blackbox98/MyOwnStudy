import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static boolean[][] v;
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int[] answer = new int[2];
        v = new boolean[n][m];
        queue = new LinkedList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (map[r][c] == 0 || v[r][c]) continue;
                answer[0]++;
                answer[1] = Math.max(answer[1], bfs(r, c));
            }
        }
        System.out.printf("%d\n%d\n", answer[0], answer[1]);
    }

    private static int bfs(int sr, int sc) {
        int cnt = 0;
        queue.offer(new int[]{sr, sc});
        v[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            cnt++;
            for (int[] d : dir) {
                int nr = point[0] + d[0];
                int nc = point[1] + d[1];
                if (check(nr, nc) || map[nr][nc] == 0 || v[nr][nc]) continue;
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
            }
        }
        return cnt;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= m;
    }
}
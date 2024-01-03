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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        v = new boolean[n][m];
        int[] goal = new int[2];
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] != 1) {
                    if (map[r][c] == 2) goal = new int[]{r, c};
                    v[r][c] = true;
                }
            }
        }
        System.out.println(bfs(goal));
    }

    private static StringBuilder bfs(int[] goal) {
        int[][] ans = new int[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                ans[r][c] = Integer.MAX_VALUE;
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{goal[0], goal[1], 0});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            if (ans[p[0]][p[1]] <= p[2]) continue;
            else ans[p[0]][p[1]] = p[2];
            for (int[] d : dir) {
                int nr = p[0] + d[0];
                int nc = p[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] == 0 || ans[nr][nc] <= p[2] + 1) continue;
                queue.offer(new int[]{nr, nc, p[2] + 1});
                v[nr][nc] = true;
            }
        }
        return printMap(ans, v);
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= m;
    }

    private static StringBuilder printMap(int[][] map, boolean[][] v) {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                sb.append(v[r][c] ? map[r][c] == Integer.MAX_VALUE ? 0 : map[r][c] : -1).append(" ");
            }
            sb.append("\n");
        }
        return sb;
    }
}
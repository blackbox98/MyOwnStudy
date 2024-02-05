import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        int[] start = new int[3];
        boolean flag = false;
        for (int r = 0; r < n; r++) {
            map[r] = br.readLine().toCharArray();
            if (flag) continue;
            for (int c = 0; c < m; c++) {
                if (map[r][c] == '2') {
                    start = new int[]{r, c, 0};
                    flag = true;
                    break;
                }
            }
        }
        System.out.println(bfs(start));
    }

    private static String bfs(int[] start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        boolean[][] v = new boolean[n][m];
        v[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] == '1') continue;
                if (map[nr][nc] > '2') return "TAK\n" + (cur[2] + 1);
                queue.offer(new int[]{nr, nc, cur[2] + 1});
                v[nr][nc] = true;
            }
        }
        return "NIE";
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= m;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, D;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        int[] start = new int[5];
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
            for (int c = 0; c < N; c++) {
                if (map[r][c] == 'S') start = new int[]{r, c, H, 0, 0};
            }
        }
        System.out.println(bfs(start));
    }

    private static int bfs(int[] start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        int[][] v = new int[N][N];
        v[start[0]][start[1]] = start[2];
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc)) continue;
                if (map[nr][nc] == 'E') return cur[4] + 1;
                int curH = cur[2];
                int curU = cur[3];
                if (map[nr][nc] == 'U') curU = D;
                if (curU == 0) curH--;
                else curU--;
                if (curH == 0 || v[nr][nc] >= curH + curU) continue;
                queue.offer(new int[]{nr, nc, curH, curU, cur[4] + 1});
                v[nr][nc] = curH + curU;
            }
        }
        return -1;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M, N;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static boolean[][] v;
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[M][N];
        for (int r = 0; r < M; r++) {
            map[r] = br.readLine().toCharArray();
        }
        boolean answer = false;
        v = new boolean[M][N];
        for (int c = 0; c < N; c++) {
            if (!v[0][c] && map[0][c] == '0' && bfs(c)) {
                answer = true;
                break;
            }
        }
        System.out.println(answer ? "YES" : "NO");
    }

    private static boolean bfs(int sc) {
        queue = new LinkedList<>();
        queue.offer(new int[]{0, sc});
        v[0][sc] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == M - 1) return true;
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] == '1') continue;
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
            }
        }
        return false;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= M || c < 0 || c >= N;
    }
}
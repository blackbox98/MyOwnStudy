import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}}; // 우 하

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(bfs(0, 0));
    }

    private static String bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});
        boolean[][] v = new boolean[N][N];
        v[r][c] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int w = map[cur[0]][cur[1]];
            for (int[] d : dir) {
                int nr = cur[0] + d[0] * w;
                int nc = cur[1] + d[1] * w;
                if (check(nr, nc) || v[nr][nc]) continue;
                if (map[nr][nc] == -1) return "HaruHaru";
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
            }
        }
        return "Hing";
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}
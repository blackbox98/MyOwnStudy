import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean[][] map;
    static boolean[][] v;
    static Queue<int[]> queue;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new boolean[N + 1][M + 1];
        v = new boolean[N + 1][M + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = true;
        }
        int answer = 0;
        queue = new LinkedList<>();
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                if (!v[r][c] && map[r][c]) answer = Math.max(answer, bfs(r, c));
            }
        }
        System.out.println(answer);
    }

    private static int bfs(int sr, int sc) {
        int cnt = 0;
        queue.add(new int[]{sr, sc});
        v[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            cnt++;
            for (int[] d : dir) {
                int nr = point[0] + d[0];
                int nc = point[1] + d[1];
                if (check(nr, nc) || !map[nr][nc] || v[nr][nc]) continue;
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
            }
        }
        return cnt;
    }

    private static boolean check(int r, int c) {
        return r < 1 || r > N || c < 1 || c > M;
    }
}
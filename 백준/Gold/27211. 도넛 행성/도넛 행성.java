import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] v;
    static Queue<int[]> queue;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;
        v = new boolean[N][M];
        queue = new LinkedList<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 1 || v[r][c]) continue;
                bfs(r, c);
                answer++;
            }
        }
        System.out.println(answer);
    }

    private static void bfs(int sr, int sc) {
        queue.offer(new int[]{sr, sc});
        v[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : dir) {
                int nr = getPoint(cur[0] + d[0], N);
                int nc = getPoint(cur[1] + d[1], M);
                if (nr < 0) nr = N - 1;
                if (nr >= N) nr = 0;
                if (nc < 0) nc = M - 1;
                if (nc >= M) nc = 0;
                if (v[nr][nc] || map[nr][nc] == 1) continue;
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
            }
        }
    }

    private static int getPoint(int p, int limit) {
        if (p < 0) p = limit - 1;
        else if (p >= limit) p = 0;
        return p;
    }
}
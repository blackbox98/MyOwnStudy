import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, A, B;
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = 1;
        }
        st = new StringTokenizer(br.readLine());
        int[] S = new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0};
        st = new StringTokenizer(br.readLine());
        int[] E = new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
        System.out.println(bfs(S, E));
    }

    private static int bfs(int[] S, int[] E) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(S);
        boolean[][] v = new boolean[N][M];
        v[S[0]][S[1]] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == E[0] && cur[1] == E[1]) return cur[2];
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (!check(nr, nc) || v[nr][nc]) continue;
                queue.offer(new int[]{nr, nc, cur[2] + 1});
                v[nr][nc] = true;
            }
        }
        return -1;
    }

    private static boolean check(int sr, int sc) {
        int nr = sr + A - 1;
        int nc = sc + B - 1;
        if (sr < 0 || sr >= N || sc < 0 || sc >= M) return false;
        if (nr < 0 || nr >= N || nc < 0 || nc >= M) return false;
        for (int r = sr; r <= nr; r++) {
            if (map[r][sc] == 1 || map[r][nc] == 1) return false;
        }
        for (int c = sc; c <= nc; c++) {
            if (map[sr][c] == 1 || map[nr][c] == 1) return false;
        }
        return true;
    }
}
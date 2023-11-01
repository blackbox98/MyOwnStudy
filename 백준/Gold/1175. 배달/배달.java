import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static boolean[][][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        int[] S = new int[2];
        int[][] C = new int[2][2];
        int idx = 0;
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 'S') S = new int[]{r, c};
                else if (map[r][c] == 'C') C[idx++] = new int[]{r, c};
            }
        }
        System.out.println(bfs(S, C));
    }

    private static int bfs(int[] S, int[][] C) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{S[0], S[1], -1, 0, 0});
        boolean[][][][] v = new boolean[N][M][4][3];
        for (int i = 0; i < 4; i++) {
            v[S[0]][S[1]][i][0] = true;
        }
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int r = point[0];
            int c = point[1];
            int d = point[2];
            int status = point[3];
            int dist = point[4];
            if (r == C[0][0] && c == C[0][1]) status |= 1;
            if (r == C[1][0] && c == C[1][1]) status |= 2;
            if (status == 3) {
                return dist;
            }
            for (int i = 0; i < 4; i++) {
                if (i == d) continue;
                int nr = r + dir[i][0];
                int nc = c + dir[i][1];
                if (check(nr, nc) || map[nr][nc] == '#' || v[nr][nc][i][status]) continue;
                queue.offer(new int[]{nr, nc, i, status, dist + 1});
                v[nr][nc][i][status] = true;
            }
        }
        return -1;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}
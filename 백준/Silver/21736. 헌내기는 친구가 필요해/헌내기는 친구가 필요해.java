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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        int[] I = new int[2];
        boolean flag = false;
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
            if (flag) continue;
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 'I') {
                    I = new int[]{r, c};
                    flag = true;
                }
            }
        }
        System.out.println(bfs(I));
    }

    private static String bfs(int[] I) {
        int cnt = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{I[0], I[1]});
        boolean[][] v = new boolean[N][M];
        v[I[0]][I[1]] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (map[cur[0]][cur[1]] == 'P') cnt++;
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] == 'X') continue;
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
            }
        }
        return cnt == 0 ? "TT" : cnt + "";
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}
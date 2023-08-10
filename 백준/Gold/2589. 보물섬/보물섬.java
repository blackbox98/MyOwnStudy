import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C, answer;
    static char[][] map;
    static boolean[][] v;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        }
        answer = 0;
        v = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] == 'L') {
                    v = new boolean[R][C];
                    answer = Math.max(answer, bfs(r, c));
                }
            }
        }
        System.out.println(answer);
    }

    private static int bfs(int sr, int sc) {
        int maxDist = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc, 0});
        v[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            for (int[] d : dir) {
                int nr = point[0] + d[0];
                int nc = point[1] + d[1];
                int nd = point[2] + 1;
                if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] == 'L' && !v[nr][nc]) {
                    queue.offer(new int[]{nr, nc, nd});
                    v[nr][nc] = true;
                    maxDist = Math.max(maxDist, nd);
                }
            }
        }
        return maxDist;
    }
}
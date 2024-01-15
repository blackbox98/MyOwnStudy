import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][] v;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[M][N];
        for (int r = 0; r < M; r++) {
            map[r] = br.readLine().toCharArray();
        }
        int[] answer = new int[2];
        v = new boolean[M][N];
        queue = new LinkedList<>();
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (v[r][c]) continue;
                if (map[r][c] == 'W') answer[0] += bfs(r, c, map[r][c]);
                else answer[1] += bfs(r, c, map[r][c]);
            }
        }
        System.out.println(answer[0] + " " + answer[1]);
    }

    private static int bfs(int sr, int sc, char ch) {
        int cnt = 0;
        queue.offer(new int[]{sr, sc});
        v[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            cnt++;
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] != ch) continue;
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
            }
        }
        return cnt * cnt;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= M || c < 0 || c >= N;
    }
}
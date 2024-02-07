import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
        }
        st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken()) - 1;
        int y1 = Integer.parseInt(st.nextToken()) - 1;
        int x2 = Integer.parseInt(st.nextToken()) - 1;
        int y2 = Integer.parseInt(st.nextToken()) - 1;
        System.out.println(bfs(x1, y1, x2, y2));
    }

    private static int bfs(int x1, int y1, int x2, int y2) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        queue.offer(new int[]{x1, y1, 0});
        int[][] v = new int[N][M];
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == x2 && cur[1] == y2) return cur[2];
            for (int[] d : dir) {
                int nr = cur[0];
                int nc = cur[1];
                for (int k = 1; k <= K; k++) {
                    nr += d[0];
                    nc += d[1];
                    if (check(nr, nc) || map[nr][nc] == '#') break;
                    if (v[nr][nc] != 0) {
                        if (v[nr][nc] == cur[2] + 1) continue;
                        else break;
                    }
                    queue.offer(new int[]{nr, nc, cur[2] + 1});
                    v[nr][nc] = cur[2] + 1;
                }
            }
        }
        return -1;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}
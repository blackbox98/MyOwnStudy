import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static int[][][] map;
    static Queue<int[]> queue;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        if (K == 1) {
            System.out.println(0);
            return;
        }
        int R = Integer.parseInt(st.nextToken());
        map = new int[N][N][5];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken()) - 1;
            int sc = Integer.parseInt(st.nextToken()) - 1;
            int er = Integer.parseInt(st.nextToken()) - 1;
            int ec = Integer.parseInt(st.nextToken()) - 1;
            for (int d = 0; d < 4; d++) {
                if (er == sr + dir[d][0] && ec == sc + dir[d][1]) {
                    map[sr][sc][d] = -1;
                    map[er][ec][(d + 2) % 4] = -1;
                    break;
                }
            }
        }
        int[][] cows = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i][0] = Integer.parseInt(st.nextToken()) - 1;
            cows[i][1] = Integer.parseInt(st.nextToken()) - 1;
            map[cows[i][0]][cows[i][1]][4] = i + 1;
        }
        int answer = 0;
        queue = new LinkedList<>();
        int idx = 0;
        while (K-- > 1) {
            map[cows[idx][0]][cows[idx][1]][4] = 0;
            answer += K - bfs(cows[idx++]);
        }
        System.out.println(answer);
    }

    private static int bfs(int[] cow) {
        int cnt = 0;
        queue.offer(cow);
        v = new boolean[N][N];
        v[cow[0]][cow[1]] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dir[d][0];
                int nc = cur[1] + dir[d][1];
                if (check(nr, nc) || v[nr][nc] || map[cur[0]][cur[1]][d] == -1) continue;
                if (map[nr][nc][4] > 0) cnt++;
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
            }
        }
        return cnt;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}
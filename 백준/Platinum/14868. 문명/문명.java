import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static int[][] map, distMap;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static PriorityQueue<int[]> pq;
    static Queue<int[]> queue;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = -1;
        }
        distMap = new int[N][N];
        pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        queue = new LinkedList<>();
        int num = 1;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == -1) {
                    grouping(r, c, num++);
                }
            }
        }
        int left = 0;
        int right = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                right = Math.max(right, distMap[r][c]);
            }
        }
        int answer = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (bfs(mid)) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else left = mid + 1;
        }
        System.out.println(answer);
    }

    private static void grouping(int sr, int sc, int num) {
        queue.offer(new int[]{sr, sc});
        v = new boolean[N][N];
        v[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            map[cur[0]][cur[1]] = num;
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc]) continue;
                if (map[nr][nc] == -1) queue.offer(new int[]{nr, nc});
                else if (map[nr][nc] == 0) pq.offer(new int[]{nr, nc, 0});
                v[nr][nc] = true;
            }
        }
        if (!pq.isEmpty()) getDist();
    }

    private static void getDist() {
        v = new boolean[N][N];
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (v[cur[0]][cur[1]]) continue;
            v[cur[0]][cur[1]] = true;
            int nd = cur[2] + 1;
            distMap[cur[0]][cur[1]] = nd;
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] != 0 || (distMap[nr][nc] != 0 && distMap[nr][nc] <= nd))
                    continue;
                pq.offer(new int[]{nr, nc, nd});
            }
        }
    }

    private static boolean bfs(int mid) {
        boolean flag = false;
        v = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (v[r][c] || distMap[r][c] > mid) continue;
                if (flag) return false;
                queue.offer(new int[]{r, c});
                v[r][c] = true;
                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    for (int[] d : dir) {
                        int nr = cur[0] + d[0];
                        int nc = cur[1] + d[1];
                        if (check(nr, nc) || v[nr][nc] || distMap[nr][nc] > mid) continue;
                        queue.offer(new int[]{nr, nc});
                        v[nr][nc] = true;
                    }
                }
                flag = true;
            }
        }
        return flag;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}
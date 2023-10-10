import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] checkDif;
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
        boolean flag = true;
        checkDif = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                int n = Integer.parseInt(st.nextToken());
                if (map[r][c] != n) {
                    checkDif[r][c] = n;
                    flag = false;
                }
            }
        }
        if (flag) {
            System.out.println("YES");
            return;
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (checkDif[r][c] == 0) continue;
                if (flag || !bfs(r, c)) {
                    System.out.println("NO");
                    return;
                }
                flag = true;
            }
        }
        System.out.println("YES");
    }

    private static boolean bfs(int sr, int sc) {
        int base = checkDif[sr][sc];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        boolean[][] v = new boolean[N][M];
        v[sr][sc] = true;
        checkDif[sr][sc] = 0;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            for (int[] d : dir) {
                int nr = point[0] + d[0];
                int nc = point[1] + d[1];
                if (check(nr, nc) || v[nr][nc]) continue;
                if (checkDif[nr][nc] > 0) {
                    if (base != checkDif[nr][nc]) continue;
                    queue.offer(new int[]{nr, nc});
                    checkDif[nr][nc] = 0;
                    v[nr][nc] = true;
                } else if (!v[nr][nc] && map[sr][sc] == map[nr][nc]) return false;
            }
        }
        return true;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}
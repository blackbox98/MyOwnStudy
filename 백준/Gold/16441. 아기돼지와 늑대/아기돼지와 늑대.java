import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    static Queue<int[]> queue;
    static boolean[][] v;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        queue = new LinkedList<>();
        v = new boolean[N][M];
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
            for (int c = 0; c < M; c++) {
                if (map[r][c] != 'W') continue;
                queue.offer(new int[]{r, c});
                v[r][c] = true;
            }
        }
        bfs();
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                sb.append(!v[r][c] && map[r][c] == '.' ? 'P' : map[r][c]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            L:
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (v[nr][nc] || map[nr][nc] == '#') continue;
                if (map[nr][nc] == '.') {
                    queue.offer(new int[]{nr, nc});
                    v[nr][nc] = true;
                } else if (map[nr][nc] == '+') {
                    while (true) {
                        nr += d[0];
                        nc += d[1];
                        if (v[nr][nc]) continue L;
                        if (map[nr][nc] == '#') {
                            nr -= d[0];
                            nc -= d[1];
                            break;
                        }
                        if (map[nr][nc] == '.') break;
                    }
                    queue.offer(new int[]{nr, nc});
                    v[nr][nc] = true;
                }
            }
        }
    }
}
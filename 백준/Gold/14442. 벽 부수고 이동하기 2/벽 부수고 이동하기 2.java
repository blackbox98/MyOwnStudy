import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
        }
        System.out.println(bfs(0, 0));
    }
    
    private static int bfs(int sr, int sc) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc, 0});
        int[][][] v = new int[N][M][K + 1];
        v[sr][sc][0] = 1;
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            if (p[0] == N - 1 && p[1] == M - 1) return v[p[0]][p[1]][p[2]];
            int nd = v[p[0]][p[1]][p[2]] + 1;
            for (int[] d : dir) {
                int nr = p[0] + d[0];
                int nc = p[1] + d[1];
                if (check(nr, nc)) continue;
                if (map[nr][nc] == '1') {
                    if (p[2] < K) {
                        if (v[nr][nc][p[2] + 1] == 0 || v[nr][nc][p[2] + 1] > nd) {
                            queue.offer(new int[]{nr, nc, p[2] + 1});
                            v[nr][nc][p[2] + 1] = nd;
                        }
                    }
                } else if (v[nr][nc][p[2]] == 0 || v[nr][nc][p[2]] > nd) {
                    queue.offer(new int[]{nr, nc, p[2]});
                    v[nr][nc][p[2]] = nd;
                }
            }
        }
        return -1;
    }
    
    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}
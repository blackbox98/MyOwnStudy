import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
        }
        System.out.println(bfs(0, 0, K));
    }
    
    private static int bfs(int sr, int sc, int K) {
        Queue<int[]> queue = new LinkedList();
        queue.offer(new int[]{sr, sc, 1, 0, 1});
        boolean[][][] v = new boolean[N][M][K + 1];
        v[sr][sc][0] = true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int pr = point[0];
            int pc = point[1];
            int pk = point[3];
            if (pr == N - 1 && pc == M - 1) return point[2];
            int nd = point[2] + 1;
            for (int[] d : dir) {
                int nr = pr + d[0];
                int nc = pc + d[1];
                if (check(nr, nc)) continue;
                if (map[nr][nc] == '0' && !v[nr][nc][pk]) {
                    queue.offer(new int[]{nr, nc, nd, pk, point[4] * -1});
                    v[nr][nc][pk] = true;
                } else {
                    if (pk == K) continue;
                    if (point[4] == 1) {
                        if (!v[nr][nc][pk + 1]) {
                            queue.offer(new int[]{nr, nc, nd, pk + 1, point[4] * -1});
                            v[nr][nc][pk + 1] = true;
                        }
                    } else {
                        queue.offer(new int[]{pr, pc, nd, pk, point[4] * -1});
                    }
                }
            }
        }
        return -1;
    }
    
    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}
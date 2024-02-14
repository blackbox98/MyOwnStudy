import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C;
    static int[][] map;
    static List<int[]> dir;
    static PriorityQueue<int[]> pq;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int N = Integer.parseInt(br.readLine());
        dir = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            dir.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        v = new boolean[R][C];
        for (int c = 0; c < C; c++) {
            if (map[0][c] == 0) continue;
            pq.offer(new int[]{0, c, 0});
            v[0][c] = true;
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[0] == R - 1) return cur[2];
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] == 0) continue;
                pq.offer(new int[]{nr, nc, cur[2] + 1});
                v[nr][nc] = true;
            }
        }
        return -1;
    }

    private static boolean check(int r, int c) {
        return r < 0 | r >= R || c < 0 || c >= C;
    }
}
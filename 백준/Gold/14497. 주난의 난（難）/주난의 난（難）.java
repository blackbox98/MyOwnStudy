import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        st = new StringTokenizer(br.readLine());
        int[] start = new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 1};
        int[] end = new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0};
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
        }
        System.out.println(bfs(start, end));
    }

    private static int bfs(int[] start, int[] end) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        pq.offer(start);
        boolean[][] v = new boolean[N][M];
        v[start[0]][start[1]] = true;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[0] == end[0] && cur[1] == end[1]) return cur[2];
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc]) continue;
                pq.offer(new int[]{nr, nc, map[nr][nc] == '1' ? cur[2] + 1 : cur[2]});
                v[nr][nc] = true;
            }
        }
        return 1;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static List<int[][]> dir;
    static PriorityQueue<int[]> pq;
    static boolean[][][][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        int[] start = new int[2];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1) start = new int[]{r, c};
            }
        }
        dir = new ArrayList<>();
        setDir();
        System.out.println(bfs(start));
    }

    private static int bfs(int[] start) {
        pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[4]));
        pq.offer(new int[]{start[0], start[1], 1, 0, 0});
        pq.offer(new int[]{start[0], start[1], 1, 1, 0});
        pq.offer(new int[]{start[0], start[1], 1, 2, 0});
        v = new boolean[N][N][N * N + 1][3];
        v[start[0]][start[1]][1][0] = true;
        v[start[0]][start[1]][1][1] = true;
        v[start[0]][start[1]][1][2] = true;
        int dist = 0;
        int end = N * N;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNum = cur[2];
            if (map[cur[0]][cur[1]] == curNum + 1) curNum++;
            if (curNum == end) {
                dist = cur[4];
                break;
            }
            for (int i = 0; i < 3; i++) {
                if (i == cur[3] || v[cur[0]][cur[1]][curNum][i]) continue;
                pq.offer(new int[]{cur[0], cur[1], curNum, i, cur[4] + 1});
                v[cur[0]][cur[1]][curNum][i] = true;
            }
            for (int[] d : dir.get(cur[3])) {
                if (cur[3] == 0) moveKnight(cur, d, curNum, cur[3]);
                else moveOthers(cur, d, curNum, cur[3]);
            }
        }
        return dist;
    }

    private static void moveKnight(int[] cur, int[] d, int curNum, int curDir) {
        int nr = cur[0] + d[0];
        int nc = cur[1] + d[1];
        if (check(nr, nc) || v[nr][nc][curNum][curDir]) return;
        pq.offer(new int[]{nr, nc, curNum, curDir, cur[4] + 1});
        v[nr][nc][curNum][curDir] = true;
    }

    private static void moveOthers(int[] cur, int[] d, int curNum, int curDir) {
        int k = 1;
        while (true) {
            int nr = cur[0] + d[0] * k;
            int nc = cur[1] + d[1] * k;
            if (check(nr, nc)) break;
            k++;
            if (v[nr][nc][curNum][curDir]) continue;
            pq.offer(new int[]{nr, nc, curNum, curDir, cur[4] + 1});
            v[nr][nc][curNum][curDir] = true;
        }
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    private static void setDir() {
        dir.add(new int[][]{{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}}); // 나이트
        dir.add(new int[][]{{-1, 1}, {1, 1}, {1, -1}, {-1, -1}}); // 비숍
        dir.add(new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}); // 룩
    }
}
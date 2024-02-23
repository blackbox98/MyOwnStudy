import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static int[][][] distMap;
    static Queue<int[]> queue;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
        }
        distMap = new int[3][N][M];
        queue = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken()) - 1;
            int sc = Integer.parseInt(st.nextToken()) - 1;
            for (int r = 0; r < N; r++) {
                Arrays.fill(distMap[i][r], Integer.MAX_VALUE);
            }
            getDist(sr, sc, i);
        }
        int[] answer = new int[]{Integer.MAX_VALUE, 0};
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == '1') continue;
                int max = 0;
                for (int i = 0; i < 3; i++) {
                    max = Math.max(max, distMap[i][r][c]);
                }
                if (max == Integer.MAX_VALUE) continue;
                if (max < answer[0]) {
                    answer = new int[]{max, 1};
                } else if (max == answer[0]) answer[1]++;
            }
        }
        System.out.println(answer[1] == 0 ? "-1" : answer[0] + "\n" + answer[1]);
    }

    private static void getDist(int sr, int sc, int idx) {
        queue.offer(new int[]{sr, sc, 0});
        v = new boolean[N][M];
        v[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            distMap[idx][cur[0]][cur[1]] = cur[2];
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] == '1') continue;
                queue.offer(new int[]{nr, nc, cur[2] + 1});
                v[nr][nc] = true;
            }
        }
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}
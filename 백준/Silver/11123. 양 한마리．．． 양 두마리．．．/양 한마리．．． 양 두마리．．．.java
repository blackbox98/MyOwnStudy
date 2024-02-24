import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int H, W;
    static char[][] map;
    static Queue<int[]> queue;
    static boolean[][] v;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int answer;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            for (int r = 0; r < H; r++) {
                map[r] = br.readLine().toCharArray();
            }
            answer = 0;
            queue = new LinkedList<>();
            v = new boolean[H][W];
            for (int r = 0; r < H; r++) {
                for (int c = 0; c < W; c++) {
                    if (v[r][c] || map[r][c] == '.') continue;
                    bfs(r, c);
                    answer++;
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs(int sr, int sc) {
        queue.offer(new int[]{sr, sc});
        v[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] == '.') continue;
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
            }
        }
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= H || c < 0 || c >= W;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int H, W;
    static char[][] map;
    static int[][] fireMap;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            fireMap = new int[H][W];
            int[] S = new int[2];
            Queue<int[]> fireList = new LinkedList<>();
            for (int r = 0; r < H; r++) {
                Arrays.fill(fireMap[r], Integer.MAX_VALUE);
                char[] tmp = br.readLine().toCharArray();
                for (int c = 0; c < W; c++) {
                    map[r][c] = tmp[c];
                    if (map[r][c] == '@') {
                        S = new int[]{r, c};
                        map[r][c] = '.';
                    } else if (map[r][c] == '*') {
                        fireList.offer(new int[]{r, c, 0});
                        map[r][c] = '.';
                        fireMap[r][c] = 0;
                    }
                }
            }
            burning(fireList);
            int answer = escapeMaze(S[0], S[1]);
            System.out.println(answer == 0 ? "IMPOSSIBLE" : answer);
        }
    }

    private static void burning(Queue<int[]> fireList) {
        while (!fireList.isEmpty()) {
            int[] fire = fireList.poll();
            int nt = fire[2] + 1;
            for (int[] d : dir) {
                int nr = fire[0] + d[0];
                int nc = fire[1] + d[1];
                if (checkRange(nr, nc) || map[nr][nc] == '#') continue;
                if (fireMap[nr][nc] > nt) {
                    fireMap[nr][nc] = nt;
                    fireList.add(new int[]{nr, nc, nt});
                }
            }
        }
    }

    private static int escapeMaze(int sr, int sc) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc, 0});
        boolean[][] v = new boolean[H][W];
        v[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int nt = point[2] + 1;
            for (int[] d : dir) {
                int nr = point[0] + d[0];
                int nc = point[1] + d[1];
                if (checkRange(nr, nc)) return nt;
                if (map[nr][nc] == '#' || v[nr][nc] || fireMap[nr][nc] <= nt) continue;
                queue.offer(new int[]{nr, nc, nt});
                v[nr][nc] = true;
            }
        }
        return 0;
    }

    private static boolean checkRange(int r, int c) {
        return r < 0 || r >= H || c < 0 || c >= W;
    }
}
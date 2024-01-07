import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static int[] nums;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static Queue<int[]> queue;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        }
        nums = new int[2];
        queue = new LinkedList<>();
        v = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] == '#' || v[r][c]) continue;
                bfs(r, c);
            }
        }
        System.out.println(nums[0] + " " + nums[1]);
    }

    private static void bfs(int sr, int sc) {
        int cntSheep = 0;
        int cntWolves = 0;
        queue.offer(new int[]{sr, sc});
        v[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (map[cur[0]][cur[1]] == 'o') cntSheep++;
            else if (map[cur[0]][cur[1]] == 'v') cntWolves++;
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] == '#') continue;
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
            }
        }
        if (cntSheep > cntWolves) nums[0] += cntSheep;
        else nums[1] += cntWolves;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }
}
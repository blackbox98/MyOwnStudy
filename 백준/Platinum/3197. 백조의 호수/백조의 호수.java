import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static int[][] LPoint;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static Queue<int[]> waterQueue, LQueue, meltingQueue;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        LPoint = new int[2][2];
        waterQueue = new LinkedList<>();
        int idx = 0;
        for (int r = 0; r < R; r++) {
            String s = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = s.charAt(c);
                if (map[r][c] == 'L') LPoint[idx++] = new int[]{r, c};
                if (map[r][c] != 'X') waterQueue.offer(new int[]{r, c});
            }
        }
        int answer = 0;
        LQueue = new LinkedList<>();
        LQueue.offer(LPoint[0]);
        v = new boolean[R][C];
        v[LPoint[0][0]][LPoint[0][1]] = true;
        while (!checkMet()) {
            meltingIce();
            answer++;
        }
        System.out.println(answer);
    }

    private static boolean checkMet() {
        meltingQueue = new LinkedList<>();
        v[LPoint[0][0]][LPoint[0][1]] = true;
        while (!LQueue.isEmpty()) {
            int[] cur = LQueue.poll();
            if (cur[0] == LPoint[1][0] && cur[1] == LPoint[1][1]) return true;
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc]) continue;
                v[nr][nc] = true;
                if (map[nr][nc] != 'X') LQueue.offer(new int[]{nr, nc});
                else meltingQueue.offer(new int[]{nr, nc});
            }
        }
        LQueue = meltingQueue;
        return false;
    }

    private static void meltingIce() {
        int size = waterQueue.size();
        for (int i = 0; !waterQueue.isEmpty() && i < size; i++) {
            int[] cur = waterQueue.poll();
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || map[nr][nc] != 'X') continue;
                waterQueue.offer(new int[]{nr, nc});
                map[nr][nc] = '.';
            }
        }
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }
}
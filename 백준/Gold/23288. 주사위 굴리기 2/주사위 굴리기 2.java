import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, x, y, d;
    static int[] dice = new int[]{1, 5, 6, 2, 4, 3};
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static Queue<int[]> queue;
    static boolean[][] v;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;
        x = 0;
        y = 0;
        d = 0;
        queue = new LinkedList<>();
        while (K-- > 0) {
            answer += play();
        }
        System.out.println(answer);
    }
    
    private static int play() {
        if (check(x + dir[d][0], y + dir[d][1])) d = (d + 2) % 4;
        x += dir[d][0];
        y += dir[d][1];
        rollDice();
        turnDir();
        return getScore();
    }
    
    private static void rollDice() {
        int tmp;
        switch (d) {
            case 0:
                tmp = dice[0];
                dice[0] = dice[4];
                dice[4] = dice[2];
                dice[2] = dice[5];
                dice[5] = tmp;
                break;
            case 1:
                tmp = dice[0];
                dice[0] = dice[3];
                dice[3] = dice[2];
                dice[2] = dice[1];
                dice[1] = tmp;
                break;
            case 2:
                tmp = dice[0];
                dice[0] = dice[5];
                dice[5] = dice[2];
                dice[2] = dice[4];
                dice[4] = tmp;
                break;
            case 3:
                tmp = dice[0];
                dice[0] = dice[1];
                dice[1] = dice[2];
                dice[2] = dice[3];
                dice[3] = tmp;
                break;
            default : break;
        }
    }
    
    private static void turnDir() {
        if (dice[2] > map[x][y]) d = (d + 1) % 4;
        else if (dice[2] < map[x][y]) d = (d + 3) % 4;
    }
    
    private static int getScore() {
        int cnt = 0;
        queue.offer(new int[]{x, y});
        v = new boolean[N][M];
        v[x][y] = true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            cnt++;
            for (int[] d : dir) {
                int nx = point[0] + d[0];
                int ny = point[1] + d[1];
                if (check(nx, ny) || map[x][y] != map[nx][ny] || v[nx][ny]) continue;
                queue.offer(new int[]{nx, ny});
                v[nx][ny] = true;
            }
        }
        return map[x][y] * cnt;
    }
    
    private static boolean check(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}
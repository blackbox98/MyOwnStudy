import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, L, R;
    static int[][] map;
    static boolean[][] v;
    static boolean flag;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;
        while (true) {
            flag = false;
            v = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (v[r][c]) continue;
                    move(r, c);
                }
            }
            if (!flag) break;
            answer++;
        }
        System.out.println(answer);
    }

    private static void move(int sr, int sc) {
        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> adjQueue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        v[sr][sc] = true;
        int cnt = 0;
        int sum = 0;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            adjQueue.offer(point);
            cnt++;
            sum += map[point[0]][point[1]];
            for (int[] d : dir) {
                int nr = point[0] + d[0];
                int nc = point[1] + d[1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc]) {
                    int dif = Math.abs(map[point[0]][point[1]] - map[nr][nc]);
                    if (dif < L || dif > R) continue;
                    queue.offer(new int[]{nr, nc});
                    v[nr][nc] = true;
                }
            }
        }
        if (cnt > 1) {
            flag = true;
            int numOfPeople = sum / cnt;
            while (!adjQueue.isEmpty()) {
                int[] point = adjQueue.poll();
                map[point[0]][point[1]] = numOfPeople;
            }
        }
    }
}
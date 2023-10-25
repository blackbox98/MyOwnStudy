import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int H, W;
    static int[] S;
    static char[][] map;
    static Queue<int[]> queue;
    static boolean[][] v;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        S = new int[3];
        map = new char[H][W];
        for (int r = 0; r < H; r++) {
            char[] tmp = br.readLine().toCharArray();
            for (int c = 0; c < W; c++) {
                map[r][c] = tmp[c];
                if (map[r][c] == 'S') S = new int[]{r, c, 0};
            }
        }
        int answer = 0;
        for (int cheese = 1; cheese <= N; cheese++) {
            answer += bfs(cheese);
        }
        System.out.println(answer);
    }
    
    private static int bfs(int cheese) {
        int time = 0;
        queue = new LinkedList<>();
        queue.offer(S);
        v = new boolean[H][W];
        v[S[0]][S[1]] = true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int pr = point[0];
            int pc = point[1];
            if (map[pr][pc] - '0' == cheese) {
                time = point[2];
                S = new int[]{pr, pc, 0};
                break;
            }
            int nt = point[2] + 1;
            for (int[] d : dir) {
                int nr = pr + d[0];
                int nc = pc + d[1];
                if (check(nr, nc) || map[nr][nc] == 'X' || v[nr][nc]) continue;
                queue.offer(new int[]{nr, nc, nt});
                v[nr][nc] = true;
            }
        }
        return time;
    }
    
    private static boolean check(int r, int c) {
        return r < 0 || r >= H || c < 0 || c >= W;
    }
}
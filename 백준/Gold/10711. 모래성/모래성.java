import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int H, W;
    static char[][] map;
    static int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    /*
    0 1 2
    3 x 4
    5 6 7
    */
    static Queue<int[]> queue;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        queue = new LinkedList<>();
        for (int r = 0; r < H; r++) {
            char[] tmp = br.readLine().toCharArray();
            for (int c = 0; c < W; c++) {
                map[r][c] = tmp[c];
                if (map[r][c] == '.') queue.offer(new int[]{r, c});
            }
        }
        int answer = 0;
        while (wave(queue.size())) {
            answer++;
        }
        System.out.println(answer);
    }
    
    private static boolean wave(int size) {
        for (int i = 0; i < size; i++) {
            int[] point = queue.poll();
            for (int[] d : dir) {
                int nr = point[0] + d[0];
                int nc = point[1] + d[1];
                if (check(nr, nc) || map[nr][nc] == '.') continue;
                map[nr][nc]--;
                if (map[nr][nc] == '0') {
                    map[nr][nc] = '.';
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return !queue.isEmpty();
    }
    
    private static boolean check(int r, int c) {
        return r < 0 || r >= H || c < 0 || c >= W;
    }
}
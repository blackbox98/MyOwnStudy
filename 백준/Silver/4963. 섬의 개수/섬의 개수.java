import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static boolean[][] v;
    static int w;
    static int h;
    static int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    /*
    0 1 2
    3 * 4
    5 6 7
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            String str = br.readLine();
            if ("0 0".equals(str)) break;
            st = new StringTokenizer(str);
            int answer = 0;
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new int[h][w];
            v = new boolean[h][w];
            for (int r = 0; r < h; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < w; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            for (int r = 0; r < h; r++) {
                for (int c = 0; c < w; c++) {
                    if (!v[r][c] && map[r][c] == 1) {
                        bfs(r, c);
                        answer++;
                    }
                }
            }
            System.out.println(answer);
        }
    }

    private static void bfs(int sr, int sc) {
        Queue<int[]> queue = new LinkedList<>();
        v[sr][sc] = true;
        queue.offer(new int[]{sr, sc});
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            for (int[] d : dir){
                int nr = point[0] + d[0];
                int nc = point[1] + d[1];
                if (nr >= 0 && nr < h && nc >= 0 && nc < w && !v[nr][nc] && map[nr][nc] == 1) {
                    queue.offer(new int[] {nr, nc});
                    v[nr][nc] = true;
                }
            }
        }
    }
}

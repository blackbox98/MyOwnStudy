import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N = 8;
    static char[][] map;
    static int[][] dir = {{0, 0}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}}; // 우 우하 하 하좌 좌 좌상 상 우상
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[N][N];
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
        }
        System.out.println(bfs(N - 1, 0));
    }
    
    private static int bfs(int sr, int sc) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        boolean[][] v;
        while (!queue.isEmpty()) {
            int size = queue.size();
            v = new boolean[N][N];
            for (int i = 0; i < size; i++) {
                int[] character = queue.poll();
                int curR = character[0];
                int curC = character[1];
                if (curR == 0 && curC == N - 1) return 1;
                if (map[curR][curC] == '#') continue;
                for (int d = 0; d < dir.length; d++) {
                    int nr = curR + dir[d][0];
                    int nc = curC + dir[d][1];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N || v[nr][nc] || map[nr][nc] == '#') continue;
                    queue.offer(new int[]{nr, nc});
                    v[nr][nc] = true;
                }
            }
            moveWall();
        }
        return 0;
    }
    
    private static void moveWall() {
        for (int r = N - 1; r >= 0; r--) {
            for (int c = 0; c < N; c++) {
                if (r - 1 >= 0) {
                    if (map[r - 1][c] == '#') map[r][c] = '#';
                    map[r - 1][c] = '.';
                }
            }
        }
    }
}
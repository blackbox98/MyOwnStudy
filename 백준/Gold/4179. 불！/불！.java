import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        int[] J = new int[2];
        Queue<int[]> fireList = new LinkedList<>();
        for (int r = 0; r < R; r++) {
            char[] tmp = br.readLine().toCharArray();
            for (int c = 0; c < C; c++) {
                map[r][c] = tmp[c];
                if (map[r][c] == 'J') {
                    J = new int[]{r, c};
                    map[r][c] = '.';
                } else if (map[r][c] == 'F') {
                    fireList.offer(new int[]{r, c, '0'});
                    map[r][c] = '0';
                }
            }
        }
        burning(fireList);
        int answer = escapeMaze(J[0], J[1]);
        System.out.println(answer == 0 ? "IMPOSSIBLE" : answer);
    }

    private static void burning(Queue<int[]> fireList) {
        while (!fireList.isEmpty()) {
            int[] fire = fireList.poll();
            char nt = (char) (fire[2] + 1);
            for (int[] d : dir) {
                int nr = fire[0] + d[0];
                int nc = fire[1] + d[1];
                if (checkRange(nr, nc) || map[nr][nc] == '#') continue;
                if (map[nr][nc] == '.' || map[nr][nc] > nt) {
                    map[nr][nc] = nt;
                    fireList.add(new int[]{nr, nc, nt});
                }
            }
        }
    }

    private static int escapeMaze(int sr, int sc) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc, '0'});
        boolean[][] v = new boolean[R][C];
        v[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            char nt = (char) (point[2] + 1);
            for (int[] d : dir) {
                int nr = point[0] + d[0];
                int nc = point[1] + d[1];
                if (checkRange(nr, nc)) return nt - '0';
                if (map[nr][nc] != '.' && (map[nr][nc] == '#' || map[nr][nc] <= nt)) continue;
                if (v[nr][nc]) continue;
                queue.offer(new int[]{nr, nc, nt});
                v[nr][nc] = true;
            }
        }
        return 0;
    }

    private static boolean checkRange(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }
}
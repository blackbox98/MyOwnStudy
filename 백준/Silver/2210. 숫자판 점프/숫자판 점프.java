import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static final int N = 5;
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static Set<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[N][N];
        StringTokenizer st;
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        set = new HashSet<>();
        int NOD = 100000;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                dfs(r, c, map[r][c] * NOD, NOD);
            }
        }
        System.out.println(set.size());
    }

    private static void dfs(int r, int c, int num, int NOD) {
        if (NOD == 1) {
            set.add(num);
            return;
        }
        for (int[] d : dir) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (check(nr, nc)) continue;
            dfs(nr, nc, num + map[nr][nc] * (NOD / 10), NOD / 10);
        }
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}
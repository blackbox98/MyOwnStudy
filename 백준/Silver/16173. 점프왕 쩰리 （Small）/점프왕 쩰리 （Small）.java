import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final String SUCCESS = "HaruHaru";
    static final String FAIL = "Hing";
    static int N;
    static int[][] map;
    static String answer = FAIL;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[][] v = new boolean[N][N];
        v[0][0] = true;
        dfs(v, 0, 0);
        System.out.println(answer);
    }

    private static void dfs(boolean[][] v, int sr, int sc) {
        if (sr == N - 1 && sc == N - 1) {
            answer = SUCCESS;
            return;
        }
        int nr = map[sr][sc] + sr;
        int nc = map[sr][sc] + sc;
        if (nr < N && !v[nr][sc]) {
            v[nr][sc] = true;
            dfs(v, nr, sc);
            v[nr][sc] = false;
        }
        if (nc < N && !v[sr][nc]) {
            v[sr][nc] = true;
            dfs(v, sr, nc);
            v[sr][nc] = false;
        }
    }
}
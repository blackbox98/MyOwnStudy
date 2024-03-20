import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] answer;
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}}; // 우 하

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                int ch = st.nextToken().charAt(0);
                switch (ch) {
                    case '+':
                        map[r][c] = 6;
                        break;
                    case '-':
                        map[r][c] = 7;
                        break;
                    case '*':
                        map[r][c] = 8;
                        break;
                    default:
                        map[r][c] = ch - '0';
                        break;
                }
            }
        }
        answer = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};
        dfs(0, 0, map[0][0]);
        System.out.printf("%d %d\n", answer[0], answer[1]);
    }

    private static void dfs(int r, int c, int result) {
        if (r == N - 1 && c == N - 1) {
            if (answer[0] < result) answer[0] = result;
            if (answer[1] > result) answer[1] = result;
            return;
        }
        for (int[] d1 : dir) {
            int nr = r + d1[0];
            int nc = c + d1[1];
            if (check(nr, nc)) continue;
            for (int[] d2 : dir) {
                int nnr = nr + d2[0];
                int nnc = nc + d2[1];
                if (check(nnr, nnc)) continue;
                switch (map[nr][nc]) {
                    case 6:
                        dfs(nnr, nnc, result + map[nnr][nnc]);
                        break;
                    case 7:
                        dfs(nnr, nnc, result - map[nnr][nnc]);
                        break;
                    case 8:
                        dfs(nnr, nnc, result * map[nnr][nnc]);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}
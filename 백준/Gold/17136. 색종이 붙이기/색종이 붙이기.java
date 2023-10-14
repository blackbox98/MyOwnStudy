import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int N = 10;
    static int answer;
    static int[] papers = {0, 5, 5, 5, 5, 5};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        answer = Integer.MAX_VALUE;
        coverOne(0, 0, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void coverOne(int r, int c, int cnt) {
        if (r == N - 1 && c == N) {
            answer = Math.min(answer, cnt);
            return;
        }
        if (answer <= cnt) return;
        if (c == N) {
            coverOne(r + 1, 0, cnt);
            return;
        }
        if (map[r][c] == 1) {
            for (int size = 5; size >= 1; size--) {
                if (papers[size] > 0 && canCover(r, c, size)) {
                    coverMap(r, c, size, 0);
                    papers[size]--;
                    coverOne(r, c + 1, cnt + 1);
                    coverMap(r, c, size, 1);
                    papers[size]++;
                }
            }
        } else coverOne(r, c + 1, cnt);
    }

    private static void coverMap(int sr, int sc, int size, int type) {
        for (int r = sr; r < sr + size; r++) {
            for (int c = sc; c < sc + size; c++) {
                map[r][c] = type;
            }
        }
    }

    private static boolean canCover(int sr, int sc, int size) {
        for (int r = sr; r < sr + size; r++) {
            for (int c = sc; c < sc + size; c++) {
                if (check(r, c) || map[r][c] == 0) return false;
            }
        }
        return true;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}
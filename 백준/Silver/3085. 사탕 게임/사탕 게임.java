import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int answer = 1;
    static int N;
    static char[][] candy;
    static int[][] dir = {{0, 1}, {1, 0}}; // 우 하

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        candy = new char[N][N];
        for (int r = 0; r < N; r++) {
            candy[r] = br.readLine().toCharArray();
        }
        for (int i = 0; i < N; i++) {
            checkR(i);
            checkC(i);
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int[] d : dir) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < N && candy[r][c] != candy[nr][nc]) {
                        swap(r, c, nr, nc);
                        checkR(r);
                        checkR(nr);
                        checkC(c);
                        checkC(nc);
                        swap(r, c, nr, nc);
                    }
                }
            }
        }
        System.out.println(answer);
    }

    private static void checkR(int r) {
        char last = candy[r][0];
        int cnt = 1;
        for (int i = 1; i < N; i++) {
            if (last == candy[r][i]) cnt++;
            else {
                last = candy[r][i];
                answer = Math.max(answer, cnt);
                cnt = 1;
            }
        }
        answer = Math.max(answer, cnt);
    }

    private static void checkC(int c) {
        char last = candy[0][c];
        int cnt = 1;
        for (int i = 1; i < N; i++) {
            if (last == candy[i][c]) cnt++;
            else {
                last = candy[i][c];
                answer = Math.max(answer, cnt);
                cnt = 1;
            }
        }
        answer = Math.max(answer, cnt);
    }

    private static void swap(int r, int c, int nr, int nc) {
        char tmp = candy[r][c];
        candy[r][c] = candy[nr][nc];
        candy[nr][nc] = tmp;
    }
}
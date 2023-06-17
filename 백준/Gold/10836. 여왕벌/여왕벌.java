import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int M, N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][M];
        for (int r = 0; r < M; r++) Arrays.fill(map[r], 1);
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int zero = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());
            for (int i = M - 1; i > 0; i--) {
                if (zero != 0) {
                    zero--;
                } else if (one != 0) {
                    one--;
                    map[i][0] += 1;
                } else if (two != 0) {
                    two--;
                    map[i][0] += 2;
                }
            }
            for (int i = 0; i < M; i++) {
                if (zero != 0) {
                    zero--;
                } else if (one != 0) {
                    one--;
                    map[0][i] += 1;
                } else if (two != 0) {
                    two--;
                    map[0][i] += 2;
                }
            }
            for (int r = 1; r < M; r++) {
                for (int c = 1; c < M; c++) {
                    map[r][c] = Math.max(Math.max(map[r - 1][c], map[r][c - 1]), map[r - 1][c - 1]);
                }
            }
        }
        printMap();
    }

    private static void printMap() {
        StringBuilder answer = new StringBuilder();
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < M; c++) {
                answer.append(map[r][c] + " ");
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}
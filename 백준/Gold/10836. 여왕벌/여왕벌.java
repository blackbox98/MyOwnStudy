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
        int[] grow = new int[3];
        for (int g = 0; g < N; g++) {
            int sr = M - 1;
            int sc = 1;
            boolean turn = false;
            st = new StringTokenizer(br.readLine());
            grow[0] = Integer.parseInt(st.nextToken());
            grow[1] = Integer.parseInt(st.nextToken());
            grow[2] = Integer.parseInt(st.nextToken());
            for (int i = 0; i < 3; i++) {
                int n = grow[i];
                if (!turn) {
                    for (int r = sr; r >= 0; r--) {
                        if (n == 0) {
                            break;
                        }
                        map[r][0] += i;
                        n--;
                        sr--;
                        if (sr < 0) {
                            turn = true;
                            break;
                        }
                    }
                    if (n > 0) {
                        for (int c = sc; c < M; c++) {
                            map[0][c] += i;
                            n--;
                            sc++;
                            if (n == 0) {
                                break;
                            }
                        }
                    }
                } else {
                    for (int c = sc; c < M; c++) {
                        if (n == 0) {
                            break;
                        }
                        map[0][c] += i;
                        n--;
                        sc++;
                    }
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
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < M; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }
}

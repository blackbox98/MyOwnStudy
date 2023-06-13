import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int N = 19;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char answer = '0';
        int[] winRC = new int[2];
        map = new char[N][N];
        for (int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = st.nextToken().charAt(0);
            }
        }
        int[][] dir = {{-1, 1}, {0, 1}, {1, 1}, {1, 0}}; // 우상 우 우하 하
        L1:
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] != '0') {
                    L2:
                    for (int[] d : dir) {
                        int beforeR = r + d[0] * -1;
                        int beforeC = c + d[1] * -1;
                        int nextR = r + d[0] * 5;
                        int nextC = c + d[1] * 5;
                        if (check(map[r][c], beforeR, beforeC) || check(map[r][c], nextR, nextC)) continue;
                        for (int i = 1; i <= 4; i++) {
                            int nr = r + d[0] * i;
                            int nc = c + d[1] * i;
                            if (!check(map[r][c], nr, nc)) continue L2;
                        }
                        answer = map[r][c];
                        winRC[0] = r + 1;
                        winRC[1] = c + 1;
                        break L1;
                    }
                }
            }
        }
        System.out.println(answer);
        if (answer != '0') System.out.println(winRC[0] + " " + winRC[1]);
    }

    private static boolean check(char target, int nr, int nc) {
        if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == target) return true;
        else return false;
    }
}
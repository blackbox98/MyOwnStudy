import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static int R, C, totalPin;
    static int[] answer;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        R = 5;
        C = 9;
        char[] tmp;
        int N = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < N; tc++) {
            totalPin = 0;
            map = new char[R][C];
            for (int r = 0; r < R; r++) {
                tmp = br.readLine().toCharArray();
                for (int c = 0; c < C; c++) {
                    map[r][c] = tmp[c];
                    if (map[r][c]== 'o') totalPin++;
                }
            }
            answer = new int[]{totalPin, 0};
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (map[r][c]== 'o') dfs(r, c, totalPin, 0);
                }
            }
            System.out.printf("%d %d\n", answer[0], answer[1]);
            br.readLine();
        }
    }
    
    private static void dfs(int sr, int sc, int pinCnt, int move) {
        if (answer[0] >= pinCnt) {
            answer[0] = pinCnt;
            answer[1] = move;
        }
        
        for (int[] d : dir) {
            int nr1 = sr + d[0];
            int nc1 = sc + d[1];
            if (check(nr1, nc1) || map[nr1][nc1] != 'o') continue;
            int nr2 = nr1 + d[0];
            int nc2 = nc1 + d[1];
            if (check(nr2, nc2) || map[nr2][nc2] != '.') continue;
            map[sr][sc] = '.';
            map[nr1][nc1] = '.';
            map[nr2][nc2] = 'o';
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (map[r][c] == 'o') dfs(r, c, pinCnt - 1, move + 1);
                }
            }
            map[sr][sc] = 'o';
            map[nr1][nc1] = 'o';
            map[nr2][nc2] = '.';
        }
    }
    
    private static boolean check(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }
}
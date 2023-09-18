import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static char answer = ' ';
    static int R, C;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        }
        L:
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] != '.') continue;
                if (findErasedBlock(r, c)) {
                    System.out.printf("%d %d %c\n", r + 1, c + 1, answer);
                    break L;
                }
            }
        }
    }

    private static boolean findErasedBlock(int r, int c) {
        int cnt = 0;
        boolean[] checkDir = new boolean[4];
        int[] d;
        int nr, nc;
        for (int i = 0; i < 4; i++) {
            d = dir[i];
            nr = r + d[0];
            nc = c + d[1];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == 'M' || map[nr][nc] == 'Z' || map[nr][nc] == '.') continue;
            if (checkReturn(map[nr][nc], (i + 2) % 4)) {
                cnt++;
                checkDir[i] = true;
            }
        }
        if (cnt >= 2) {
            if (cnt == 4) answer = '+';
            else answer = findBlock(checkDir);
        }
        return cnt >= 2;
    }

    private static boolean checkReturn(char block, int d) {
        switch (block) {
            case '|':
                if (d == 1 || d == 3) return true;
                break;
            case '-':
                if (d == 0 || d == 2) return true;
                break;
            case '+':
                return true;
            case '1':
                if (d == 0 || d == 1) return true;
                break;
            case '2':
                if (d == 0 || d == 3) return true;
                break;
            case '3':
                if (d == 2 || d == 3) return true;
                break;
            case '4':
                if (d == 1 || d == 2) return true;
                break;
        }
        return false;
    }

    private static char findBlock(boolean[] checkDir) {
        char result = ' ';
        if (checkDir[1] && checkDir[3]) result = '|';
        else if (checkDir[0] && checkDir[2]) result = '-';
        else if (checkDir[0] && checkDir[1]) result = '1';
        else if (checkDir[0] && checkDir[3]) result = '2';
        else if (checkDir[2] && checkDir[3]) result = '3';
        else if (checkDir[1] && checkDir[2]) result = '4';
        return result;
    }
}
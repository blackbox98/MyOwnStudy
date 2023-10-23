import java.io.*;
import java.util.*;

public class Main {
    static final int R = 5;
    static final int C = 9;
    static char[][] map;
    static String answer;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[R][C];
        boolean[] v = new boolean['L' - 'A' + 1];
        for (int r = 0; r < R; r++) {
            char[] tmp = br.readLine().toCharArray();
            for (int c = 0; c < C; c++) {
                map[r][c] = tmp[c];
                if (map[r][c] == '.') continue;
                if (map[r][c] != 'x') v[map[r][c] - 'A'] = true;
            }
        }
        answer = null;
        magicStar(v, 0, 4);
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] == '.') sb.append(map[r][c]);
                else sb.append(answer.charAt(idx++));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    private static void magicStar(boolean[] v, int sr, int sc) {
        if (answer != null) return;
        if (sr == R - 1 && sc == 5) {
            if (!isMagicStar()) return;
            answer = "";
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (map[r][c] != '.') answer += map[r][c];
                }
            }
            return;
        }
        if (sc == C) {
            sr++;
            sc = 0;
            magicStar(v, sr, sc);
            return;
        }
        if (map[sr][sc] == '.' || map[sr][sc] != 'x') magicStar(v, sr, sc + 1);
        else {
            char originCh = map[sr][sc];
            for (char ch = 'A'; ch <= 'L'; ch++) {
                if (v[ch - 'A']) continue;
                v[ch - 'A'] = true;
                map[sr][sc] = ch;
                magicStar(v, sr, sc + 1);
                v[ch - 'A'] = false;
            }
            map[sr][sc] = originCh;
        }
    }
    
    private static boolean isMagicStar() {
        int sum = map[1][1] + map[1][3] + map[1][5] + map[1][7];
        if (sum != map[1][1] + map[2][2] + map[3][3] + map[4][4]) return false;
        if (sum != map[1][7] + map[2][6] + map[3][5] + map[4][4]) return false;
        if (sum != map[0][4] + map[1][3] + map[2][2] + map[3][1]) return false;
        if (sum != map[0][4] + map[1][5] + map[2][6] + map[3][7]) return false;
        if (sum != map[3][1] + map[3][3] + map[3][5] + map[3][7]) return false;
        return true;
    }
}
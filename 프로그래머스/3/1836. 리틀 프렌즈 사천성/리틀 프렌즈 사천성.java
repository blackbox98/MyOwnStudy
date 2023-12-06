import java.util.*;

class Solution {
    String answer;
    boolean finish;
    int m, n, total;
    List<int[]>[] list;
    
    public String solution(int m, int n, String[] board) {
        answer = "";
        finish = false;
        this.m = m;
        this.n = n;
        char[][] map = new char[m][n];
        list = new List[26];
        for (int i = 0; i < 26; i++) {
            list[i] = new ArrayList<>();
        }
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = board[r].charAt(c);
                map[r][c] = ch;
                if ('A' > ch || ch > 'Z') continue;
                list[ch - 'A'].add(new int[]{r, c});
            }
        }
        total = 0;
        for (int i = 0; i < 26; i++) {
            if (list[i].size() > 0) total++;
        }
        boolean[] v = new boolean[26];
        for (int i = 0; i < 26; i++) {
            if (list[i].size() > 0) play(map, v, i, 1, "" + (char) (i + 'A'));
        }
        return answer.equals("") ? "IMPOSSIBLE" : answer;
    }
    
    public void play(char[][] map, boolean[] v, int idx, int cnt, String result) {
        if (finish) return;

        int[] s = list[idx].get(0);
        int[] e = list[idx].get(1);

        if (!checkPath(map, (char) (idx + 'A'), s, e)) return;
        if (cnt == total) {
            answer = result;
            finish = true;
            return;
        }

        char[][] tmpMap = mapCopy(map);
        tmpMap[s[0]][s[1]] = '.';
        tmpMap[e[0]][e[1]] = '.';
        v[idx] = true;
        for (int i = 0; i < 26; i++) {
            if (v[i] || list[i].size() == 0) continue;
            play(tmpMap, v, i, cnt + 1, result + (char) (i + 'A'));
        }
    }
    
    public boolean checkPath(char[][] map, char ch, int[] s, int[] e) {
        if (s[1] <= e[1]) {
            if (rowCheck(map, s[0], e[0], s[1], ch) && colCheck(map, s[1], e[1], e[0], ch)) return true;
            if (colCheck(map, s[1], e[1], s[0], ch) && rowCheck(map, s[0], e[0], e[1], ch)) return true;
        } else {
            if (rowCheck(map, s[0], e[0], e[1], ch) && colCheck(map, e[1], s[1], s[0], ch)) return true;
            if (colCheck(map, e[1], s[1], e[0], ch) && rowCheck(map, s[0], e[0], s[1], ch)) return true;
        }
        return false;
    }

    public boolean rowCheck(char[][] map, int sr, int er, int c, char ch) {
        for (int r = sr; r <= er; r++) {
            if (map[r][c] != '.' && map[r][c] != ch)
                return false;
        }
        return true;
    }

    public boolean colCheck(char[][] map, int sc, int ec, int r, char ch) {
        for (int c = sc; c <= ec; c++) {
            if (map[r][c] != '.' && map[r][c] != ch)
                return false;
        }
        return true;
    }
    
    public char[][] mapCopy(char[][] map) {
        char[][] tmpMap = new char[m][n];
        for (int r = 0; r < m; r++) {
            tmpMap[r] = map[r].clone();
        }
        return tmpMap;
    }
}
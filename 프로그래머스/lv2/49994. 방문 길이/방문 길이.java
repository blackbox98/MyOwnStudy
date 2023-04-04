import java.util.HashSet;
import java.util.Set;

class Solution {
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static final String d = "UDRL";
    
    public int solution(String dirs) {
        int answer = 0;
        int len = 5;
        int size = len * 2 + 1;
        int sr = 0;
        int sc = 0;
        Set<Integer>[] setArrH = new Set[size];
        Set<Integer>[] setArrW = new Set[size];
        for (int i = 0; i < size; i++) {
            setArrH[i] = new HashSet<>();
            setArrW[i] = new HashSet<>();
        }
        for (char way : dirs.toCharArray()) {
            int idx = d.indexOf(way);
            int nr = sr + dir[idx][0];
            int nc = sc + dir[idx][1];
            if (nr >= -len & nr <= len && nc >= -len && nc <= len) {
                int min = Math.min(sr + len, nr + len);
                if (way == 'U' || way == 'D') setArrH[sc + len].add(Math.min(sr + len, nr + len));
                else if (way == 'R' || way == 'L') setArrW[Math.min(sc + len, nc + len)].add(sr + len);
                sr = nr;
                sc = nc;
            }
        }
        for (int i = 0; i < size; i++) {
            answer += setArrH[i].size() + setArrW[i].size();
        }
        return answer;
    }
}
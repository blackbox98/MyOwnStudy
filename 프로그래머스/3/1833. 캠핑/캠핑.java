import java.util.*;

class Solution {
    public int solution(int n, int[][] data) {
        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();
        for (int[] wedge : data) {
            xList.add(wedge[0]);
            yList.add(wedge[1]);
        }
        List<Integer> newXList = new ArrayList<>(new HashSet<>(xList));
        List<Integer> newYList = new ArrayList<>(new HashSet<>(yList));
        Collections.sort(newXList);
        Collections.sort(newYList);
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            data[i][0] = newXList.indexOf(data[i][0]);
            data[i][1] = newYList.indexOf(data[i][1]);
            map[data[i][0]][data[i][1]] = 1;
        }
        for (int i = 1; i < n; i++) {
            map[i][0] += map[i - 1][0];
            map[0][i] += map[0][i - 1];
        }
        for (int r = 1; r < n; r++) {
            for (int c = 1; c < n; c++) {
                map[r][c] += map[r - 1][c] + map[r][c - 1] - map[r - 1][c - 1];
            }
        }
        int answer = 0;
        for (int w1 = 0; w1 < n - 1; w1++) {
            for (int w2 = w1 + 1; w2 < n; w2++) {
                if (data[w1][0] == data[w2][0] || data[w1][1] == data[w2][1]) continue;
                int sr = Math.min(data[w1][0], data[w2][0]);
                int sc = Math.min(data[w1][1], data[w2][1]);
                int er = Math.max(data[w1][0], data[w2][0]);
                int ec = Math.max(data[w1][1], data[w2][1]);
                if ((sr + 1 == er || sc + 1 == ec) || (map[sr][sc] + map[er - 1][ec - 1] == map[er - 1][sc] + map[sr][ec - 1])) answer++;
            }
        }
        return answer;
    }
}
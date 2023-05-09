import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static int R;
    static int C;
    static boolean[][] v;
    
    public int[] solution(String[] maps) {
        int[] answer = {-1};
        R = maps.length;
        C = maps[0].length();
        v = new boolean[R][C];
        List<Integer> list = new ArrayList<>();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (v[r][c] || maps[r].charAt(c) == 'X') continue;
                list.add(bfs(maps, r, c));
            }
        }
        if (list.size() > 0) {
            Collections.sort(list);
            answer = new int[list.size()];
            for (int i = 0; i < list.size(); i++) answer[i] = list.get(i);
        }
        return answer;
    }
    
    private static int bfs(String[] maps, int sr, int sc) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        v[sr][sc] = true;
        int days = 0;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            days += maps[point[0]].charAt(point[1]) - '0';
            for (int[] d : dir) {
                int nr = point[0] + d[0];
                int nc = point[1] + d[1];
                if (nr >= 0 && nr < R && nc >= 0 && nc < C && !v[nr][nc] && maps[nr].charAt(nc) != 'X') {
                    queue.offer(new int[]{nr, nc});
                    v[nr][nc] = true;
                }
            }
        }
        return days;
    }
}
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static int answer = 0;
    static int R;
    static int C;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    
    public int solution(String[] maps) {
        R = maps.length;
        C = maps[0].length();
        Point start = new Point();
        Point end = new Point();
        Point lever = new Point();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (maps[r].charAt(c) == 'S') {
                    start.r = r;
                    start.c = c;
                }
                if (maps[r].charAt(c) == 'E') {
                    end.r = r;
                    end.c = c;
                }
                if (maps[r].charAt(c) == 'L') {
                    lever.r = r;
                    lever.c = c;
                }
            }
        }
        if (!bfs(maps, start, lever) || !bfs(maps, lever, end)) answer = -1;
        return answer;
    }
    
    private static boolean bfs(String[] maps, Point from, Point to) {
        boolean[][] v = new boolean[R][C];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(from.r, from.c, 0));
        v[from.r][from.c] = true;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.r == to.r && point.c == to.c) {
                answer += point.dist;
                return true;
            }
            int nDist = point.dist + 1;
            for (int[] d : dir) {
                int nr = point.r + d[0];
                int nc = point.c + d[1];
                if (nr >= 0 && nr < R && nc >= 0 && nc < C && !v[nr][nc] && maps[nr].charAt(nc) != 'X') {
                    queue.offer(new Point(nr, nc, nDist));
                    v[nr][nc] = true;
                }
            }
        }
        return false;
    }
    
    private static class Point {
        int r;
        int c;
        int dist;
        
        Point() {
            this.r = -1;
            this.c = -1;
            this.dist = 0;
        }
        
        Point (int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
}
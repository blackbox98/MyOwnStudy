import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    
    public int solution(int[][] maps) {
        return bfs(maps, new boolean[maps.length][maps[0].length], 0, 0);
    }
    
    private static int bfs(int[][] maps, boolean[][] v, int r, int c) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(r, c, 1));
        v[r][c] = true;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (p.r == maps.length - 1 && p.c == maps[0].length - 1) return p.d;
            for (int i = 0; i < dir.length; i++) {
                int nr = p.r + dir[i][0];
                int nc = p.c + dir[i][1];
                if (nr >= 0 && nr < maps.length && nc >= 0 && nc < maps[0].length && maps[nr][nc] == 1 && !v[nr][nc]) {
                    v[nr][nc] = true;
                    queue.offer(new Point(nr, nc, p.d + 1));
                }
            }
        }
        return -1;
    }
    
    private static class Point {
        int r;
        int c;
        int d;

        public Point(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}
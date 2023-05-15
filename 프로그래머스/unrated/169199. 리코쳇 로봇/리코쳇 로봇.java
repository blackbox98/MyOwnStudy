import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static String[] map;
    static boolean[][] v;
    static int R;
    static int C;
    
    public int solution(String[] board) {
        int answer = -1;
        map = board;
        R = map.length;
        C = map[0].length();
        v = new boolean[R][C];
        Point robot = new Point(0, 0, 0);
        Point goal = new Point(0, 0, 0);
        boolean flag = false;
        for (int r = 0; r < R; r++) {
            String row = map[r];
            for (int c = 0; c < C; c++) {
                char ch = row.charAt(c);
                if (ch == 'R') {
                    robot.r = r;
                    robot.c = c;
                    map[r] = map[r].replaceAll("R", ".");
                }
                if (ch == 'G') {
                    for (int[] d : dir) {
                        int nr = r + d[0];
                        int nc = c + d[1];
                        if (nr >= 0 && nr < R && nc >= 0 && nc < C && (map[nr].charAt(nc) == 'D' || nr == 0 || nr == R - 1 || nc == 0 || nc == C - 1)) {
                            flag = true;
                            goal.r = r;
                            goal.c = c;
                            break;
                        }
                    }
                }
            }
        }
        if (flag) answer = findGoal(robot, goal);
        return answer;
    }
    
    private static int findGoal(Point robot, Point goal) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(robot.r, robot.c, 0));
        v[robot.r][robot.c] = true;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.r == goal.r && point.c == goal.c) return point.depth;
            for (int[] d : dir) {
                int nr = point.r;
                int nc = point.c;
                int dep = point.depth;
                while (true) {
                    nr += d[0];
                    nc += d[1];
                    if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr].charAt(nc) == 'D') {
                        nr -= d[0];
                        nc -= d[1];
                        break;
                    }
                }
                if (!v[nr][nc]) {
                    queue.offer(new Point(nr, nc, dep + 1));
                    v[nr][nc] = true;
                }
            }
        }
        return -1;
    }

    private static class Point {
        int r;
        int c;
        int depth;

        Point(int r, int c, int depth) {
            this.r = r;
            this.c = c;
            this.depth = depth;
        }
    }
}
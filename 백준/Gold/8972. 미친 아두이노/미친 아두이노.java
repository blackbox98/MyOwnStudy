import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C;
    static int[][] dir = {{1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 0}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
    /*
    6 7 8
    3 4 5
    0 1 2
    */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        Point I = new Point(0, 0);
        Point p;
        Queue<Point> queue = new LinkedList<>();
        Map<Point, Integer> map = new HashMap<>();
        String s = " ";
        for (int r = 0; r < R; r++) {
            s = br.readLine();
            for (int c = 0; c < C; c++) {
                if (s.charAt(c) == 'I') I = new Point(r, c);
                else if (s.charAt(c) == 'R') {
                    p = new Point(r, c);
                    queue.offer(p);
                    map.put(p, map.getOrDefault(p, 0) + 1);
                }
            }
        }
        int answer = 0;
        int moveD;
        Point point, movedPoint;
        String moveOrder = br.readLine();
        L:
        for (int i = 0; i < moveOrder.length(); i++) {
            answer++;
            moveD = moveOrder.charAt(i) - '1';
            I.r += dir[moveD][0];
            I.c += dir[moveD][1];
            if (map.getOrDefault(I, 0) > 0) break;
            map = new HashMap<>();
            while (!queue.isEmpty()) {
                point = queue.poll();
                movedPoint = findShortDist(I, point);
                if (I.equals(movedPoint)) break L;
                map.put(movedPoint, map.getOrDefault(movedPoint, 0) + 1);
            }
            for (Point mp : map.keySet()) {
                if (map.get(mp) == 1) queue.offer(mp);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (answer < moveOrder.length()) sb.append("kraj ").append(answer);
        else {
            char[][] board = new char[R][C];
            for (int r = 0; r < R; r++) {
                Arrays.fill(board[r], '.');
            }
            board[I.r][I.c] = 'I';
            for (Point pt : queue) {
                board[pt.r][pt.c] = 'R';
            }
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    sb.append(board[r][c]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static Point findShortDist(Point I, Point Rp) {
        Point result = new Point(Rp.r, Rp.c);
        int nr, nc, dist;
        int minD = Integer.MAX_VALUE;
        for (int[] d : dir) {
            nr = Rp.r + d[0];
            nc = Rp.c + d[1];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
            dist = Math.abs(I.r - nr) + Math.abs(I.c - nc);
            if (minD > dist) {
                minD = dist;
                result.r = nr;
                result.c = nc;
            }
        }
        return result;
    }

    private static class Point {
        int r;
        int c;

        private Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object obj) {
            Point o = (Point) obj;
            return this.r == o.r && this.c == o.c;
        }

        @Override
        public int hashCode() {
            return r * 101 + c;
        }
    }
}
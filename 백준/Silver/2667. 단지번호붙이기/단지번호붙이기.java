import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int num;
    static List<Integer> list;
    static int[][] map;
    static boolean[][] v;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = 0;
        list = new ArrayList<>();
        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            char[] tmp = br.readLine().toCharArray();
            for (int c = 0; c < tmp.length; c++) {
                map[r][c] = tmp[c] - '0';
            }
        }
        v = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == 1 && !v[r][c]) {
                    num++;
                    bfs(r, c);
                }
            }
        }
        Collections.sort(list);
        System.out.println(num);
        for (int houseNum : list) {
            System.out.println(houseNum);
        }
    }

    private static void bfs(int sr, int sc) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(sr, sc));
        v[sr][sc] = true;
        map[sr][sc] = 0;
        int cnt = 1;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dir[d][0];
                int nc = p.c + dir[d][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc] && map[nr][nc] == 1) {
                    v[nr][nc] = true;
                    queue.offer(new Point(nr, nc));
                    map[nr][nc] = 0;
                    cnt++;
                }
            }
        }
        list.add(cnt);
    }

    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
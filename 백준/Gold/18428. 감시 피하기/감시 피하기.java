import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean success;
    static char[][] map;
    static List<Point> teachers, candidates;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        teachers = new ArrayList<>();
        StringTokenizer st;
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = st.nextToken().charAt(0);
                if (map[r][c] == 'T') teachers.add(new Point(r, c));
            }
        }
        boolean[][] v = new boolean[N][N];
        candidates = new ArrayList<>();
        for (Point teacher : teachers) {
            for (int[] d : dir) {
                int k = 1;
                while (true) {
                    int nr = teacher.r + d[0] * k;
                    int nc = teacher.c + d[1] * k;
                    if (check(nr, nc)) break;
                    k++;
                    if (map[nr][nc] == 'S' || map[nr][nc] == 'T') continue;
                    Point p = new Point(nr, nc);
                    if (!candidates.contains(p)) candidates.add(p);
                }
            }
        }
        combination(new int[3], 0, 0);
        System.out.println(success ? "YES" : "NO");
    }

    private static void combination(int[] arr, int k, int idx) {
        if (success) return;
        if (k == 3) {
            success = checkAvoid(arr);
            return;
        }

        for (int i = idx; i < candidates.size(); i++) {
            arr[k] = i;
            combination(arr, k + 1, i + 1);
        }
    }

    private static boolean checkAvoid(int[] arr) {
        char[][] tmpMap = new char[N][N];
        for (int r = 0; r < N; r++) {
            tmpMap[r] = map[r].clone();
        }
        for (int idx : arr) {
            Point obstacle = candidates.get(idx);
            tmpMap[obstacle.r][obstacle.c] = 'O';
        }
        for (Point teacher : teachers) {
            for (int[] d : dir) {
                int k = 1;
                while (true) {
                    int nr = teacher.r + d[0] * k;
                    int nc = teacher.c + d[1] * k;
                    if (check(nr, nc) || tmpMap[nr][nc] == 'O' || tmpMap[nr][nc] == 'T') break;
                    if (tmpMap[nr][nc] == 'S') return false;
                    k++;
                }
            }
        }
        return true;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
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
            return r * (N * N) + c;
        }
    }
}
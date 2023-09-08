import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int answer = 0, N, M, G, R;
    static StringBuilder sb;
    static char[][] map;
    static List<Point> list = new ArrayList<>();
    static List<Point> cmList;
    static Set<String> set = new HashSet<>();
    static Queue<Point> queue = new LinkedList<>();
    static Point[][] v;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = st.nextToken().charAt(0);
                if (map[r][c] == '2') list.add(new Point(r, c, 0, '2'));
            }
        }
        permutation(new int[G + R], new boolean[list.size()], 0);
        System.out.println(answer);
    }

    private static void permutation(int[] arr, boolean[] v, int idx) {
        if (idx == G + R) {
            sb = new StringBuilder();
            L:
            for (int n = 0; n < list.size(); n++) {
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == n) {
                        if (i < G) sb.append('G');
                        else sb.append('R');
                        continue L;
                    }
                }
                sb.append(' ');
            }
            if (set.contains(sb.toString())) return;
            set.add(sb.toString());
            cmList = new ArrayList<>();
            Point p;
            int index = 0;
            for (int i = 0; i < G; i++) {
                p = list.get(arr[index++]);
                cmList.add(new Point(p.r, p.c, 0, 'G'));
            }
            for (int i = 0; i < R; i++) {
                p = list.get(arr[index++]);
                cmList.add(new Point(p.r, p.c, 0, 'R'));
            }
            answer = Math.max(answer, bfs(cmList));
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (v[i]) continue;
            v[i] = true;
            arr[idx] = i;
            permutation(arr, v, idx + 1);
            v[i] = false;
        }
    }

    private static int bfs(List<Point> cmList) {
        int cnt = 0;
        v = new Point[N][M];
        for (Point p : cmList) {
            queue.offer(p);
            v[p.r][p.c] = new Point(p.depth, p.type);
        }
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (v[p.r][p.c].type == 'F') continue;
            for (int[] d : dir) {
                int nr = p.r + d[0];
                int nc = p.c + d[1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == '0') continue;
                int nd = p.depth + 1;
                if (v[nr][nc] == null) {
                    queue.offer(new Point(nr, nc, nd, p.type));
                    v[nr][nc] = new Point(nd, p.type);
                } else if ((v[nr][nc].type == 'G' && p.type == 'R') || (v[nr][nc].type == 'R' && p.type == 'G')) {
                    if (v[nr][nc].depth != nd) continue;
                    v[nr][nc] = new Point(nd, 'F');
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static class Point {
        int r;
        int c;
        int depth;
        char type;

        private Point(int depth, char type) {
            this.depth = depth;
            this.type = type;
        }

        private Point(int r, int c, int depth, char type) {
            this.r = r;
            this.c = c;
            this.depth = depth;
            this.type = type;
        }
    }
}
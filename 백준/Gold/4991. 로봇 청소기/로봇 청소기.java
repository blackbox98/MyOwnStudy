import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = 1000000000;
    static int w, h, n, answer;
    static char[][] map;
    static List<int[]> list;
    static int[][] graph;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String s;
        while ((s = br.readLine()) != null && !s.equals("0 0")) {
            st = new StringTokenizer(s);
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            list = new ArrayList<>();
            int robot = 0;
            for (int r = 0; r < h; r++) {
                map[r] = br.readLine().toCharArray();
                for (int c = 0; c < w; c++) {
                    if (map[r][c] == 'o' || map[r][c] == '*') {
                        list.add(new int[]{r, c});
                        if (map[r][c] == 'o') robot = list.size() - 1;
                    }
                }
            }
            n = list.size();
            graph = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) continue;
                    graph[i][j] = INF;
                }
            }
            int dist;
            for (int start = 0; start < n; start++) {
                for (int end = start + 1; end < n; end++) {
                    dist = bfs(start, end);
                    graph[start][end] = dist;
                    graph[end][start] = dist;
                }
            }
            // Floyd-Warshall
            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    if (k == i) continue;
                    for (int j = 0; j < n; j++) {
                        if (k == j || i == j) continue;
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }
            answer = Integer.MAX_VALUE;
            boolean[] v = new boolean[n];
            v[robot] = true;
            findPath(v, robot, 1, 0);
            System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
        }
    }

    private static int bfs(int s, int e) {
        int dist = INF;
        int[] start = list.get(s);
        int[] end = list.get(e);
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1], 0});
        boolean[][] v = new boolean[h][w];
        v[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            if (point[0] == end[0] && point[1] == end[1]) {
                dist = point[2];
                break;
            }
            if ((point[0] != start[0] || point[1] != start[1]) && map[point[0]][point[1]] == '*') continue;
            int nCost = point[2] + 1;
            for (int[] d : dir) {
                int nr = point[0] + d[0];
                int nc = point[1] + d[1];
                if (check(nr, nc) || map[nr][nc] == 'x' || v[nr][nc]) continue;
                queue.offer(new int[]{nr, nc, nCost});
                v[nr][nc] = true;
            }
        }
        return dist;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= h || c < 0 || c >= w;
    }

    private static void findPath(boolean[] v, int e, int k, int dist) {
        if (k == n) {
            answer = Math.min(answer, dist);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (v[i] || graph[e][i] == INF) continue;
            v[i] = true;
            findPath(v, i, k + 1, dist + graph[e][i]);
            v[i] = false;
        }
    }
}
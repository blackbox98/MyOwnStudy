import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] dir = {{-2, -1}, {-2, 1}, {0, -2}, {0, 2}, {2, -1}, {2, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] start = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        int[] end = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        System.out.println(bfs(start, end));
    }

    private static int bfs(int[] start, int[] end) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1], 0});
        int[][] map = new int[N][N];
        map[start[0]][start[1]] = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == end[0] && cur[1] == end[1]) return cur[2];
            if (map[cur[0]][cur[1]] > 0 && map[cur[0]][cur[1]] < cur[2]) continue;
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || (map[nr][nc] > 0 && map[nr][nc] <= cur[2] + 1)) continue;
                queue.offer(new int[]{nr, nc, cur[2] + 1});
                map[nr][nc] = cur[2] + 1;
            }
        }
        return -1;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}
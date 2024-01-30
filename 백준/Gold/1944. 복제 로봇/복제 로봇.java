import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static List<int[]> list;
    static List<int[]>[] adjList;
    static Queue<int[]> queue;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        list = new ArrayList<>();
        int num = 3;
        for (int r = 0; r < N; r++) {
            char[] tmp = br.readLine().toCharArray();
            for (int c = 0; c < N; c++) {
                if (tmp[c] == 'K') {
                    list.add(new int[]{r, c});
                    map[r][c] = num++;
                } else if (tmp[c] == 'S') {
                    list.add(0, new int[]{r, c});
                    map[r][c] = 2;
                } else map[r][c] = tmp[c] - '0';
            }
        }
        adjList = new List[M + 3];
        for (int i = 2; i <= M + 2; i++) {
            adjList[i] = new ArrayList<>();
        }
        queue = new LinkedList<>();
        for (int[] point : list) {
            bfs(point);
            map[point[0]][point[1]] = 0;
        }
        System.out.println(MST());
    }

    private static int MST() {
        int dist = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{2, 0});
        boolean[] visit = new boolean[M + 3];
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (visit[cur[0]]) continue;
            visit[cur[0]] = true;
            dist += cur[1];
            for (int[] next : adjList[cur[0]]) {
                if (!visit[next[0]]) pq.offer(next);
            }
        }
        for (int i = 2; i <= M + 2; i++) {
            if (!visit[i]) return -1;
        }
        return dist;
    }

    private static void bfs(int[] start) {
        int sNum = map[start[0]][start[1]];
        queue.offer(new int[]{start[0], start[1], 0});
        v = new boolean[N][N];
        v[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cNum = map[cur[0]][cur[1]];
            if (cur[2] > 0 && map[cur[0]][cur[1]] > 1) {
                adjList[sNum].add(new int[]{cNum, cur[2]});
                adjList[cNum].add(new int[]{sNum, cur[2]});
            }
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] == 1) continue;
                queue.offer(new int[]{nr, nc, cur[2] + 1});
                v[nr][nc] = true;
            }
        }
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}
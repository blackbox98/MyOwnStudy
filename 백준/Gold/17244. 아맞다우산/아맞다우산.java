import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M, N, X, answer;
    static char[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static List<int[]>[] list;
    static Queue<int[]> queue;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[M][N];
        int[] S = new int[3];
        int[] E = new int[3];
        X = 2;
        for (int r = 0; r < M; r++) {
            map[r] = br.readLine().toCharArray();
            for (int c = 0; c < N; c++) {
                if (map[r][c] == 'S') S = new int[]{r, c, 0};
                else if (map[r][c] == 'E') {
                    E = new int[]{r, c, 0};
                    map[r][c] = '1';
                } else if (map[r][c] == 'X') map[r][c] = (char) (X++ + '0');
            }
        }
        list = new List[X];
        for (int i = 0; i < X; i++) {
            list[i] = new ArrayList<>();
        }
        map[S[0]][S[1]] = '.';
        addEdge(S, 0);
        map[E[0]][E[1]] = '.';
        addEdge(E, 1);
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] > '0') {
                    addEdge(new int[]{r, c, 0}, map[r][c] - '0');
                    map[r][c] = '.';
                }
            }
        }
        answer = Integer.MAX_VALUE;
        boolean[] visit = new boolean[X];
        visit[0] = true;
        visit[1] = true;
        dfs(visit, 0, 2, 0);
        System.out.println(answer);
    }

    private static void dfs(boolean[] visit, int start, int cnt, int dist) {
        if (cnt == X) {
            for (int[] next : list[start]) {
                if (next[0] == 1) {
                    dist += next[1];
                    break;
                }
            }
            answer = Math.min(answer, dist);
            return;
        }
        for (int[] next : list[start]) {
            if (visit[next[0]]) continue;
            visit[next[0]] = true;
            dfs(visit, next[0], cnt + 1, dist + next[1]);
            visit[next[0]] = false;
        }
    }

    private static void addEdge(int[] start, int num) {
        queue = new LinkedList<>();
        queue.offer(start);
        v = new boolean[M][N];
        v[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (map[cur[0]][cur[1]] > '0') {
                list[num].add(new int[]{map[cur[0]][cur[1]] - '0', cur[2]});
                list[map[cur[0]][cur[1]] - '0'].add(new int[]{num, cur[2]});
            }
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || map[nr][nc] == '#') continue;
                queue.offer(new int[]{nr, nc, cur[2] + 1});
                v[nr][nc] = true;
            }
        }
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= M || c < 0 || c >= N;
    }
}
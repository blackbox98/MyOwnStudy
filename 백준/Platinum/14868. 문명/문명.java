import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] parent;
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        parent = new int[K + 1];
        for (int i = 1; i <= K; i++) {
            parent[i] = i;
        }
        map = new int[N][N];
        queue = new LinkedList<>();
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = i;
            queue.offer(new int[]{r, c});
        }
        int answer = 0;
        joinMap();
        while (K > 1) {
            expansionMap();
            answer++;
        }
        System.out.println(answer);
    }

    private static void joinMap() {
        int size = queue.size();
        for (int i = 0; !queue.isEmpty() && i < size; i++) {
            int[] cur = queue.poll();
            int num = map[cur[0]][cur[1]];
            queue.offer(cur);
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (check(nr, nc) || map[nr][nc] == 0 || find(num) == find(map[nr][nc])) continue;
                union(num, map[nr][nc]);
                K--;
            }
        }
    }

    private static void expansionMap() {
        int size = queue.size();
        for (int i = 0; !queue.isEmpty() && i < size; i++) {
            int[] cur = queue.poll();
            int cNum = map[cur[0]][cur[1]];
            for (int[] d1 : dir) {
                int nr = cur[0] + d1[0];
                int nc = cur[1] + d1[1];
                if (check(nr, nc) || map[nr][nc] != 0) continue;
                queue.offer(new int[]{nr, nc});
                map[nr][nc] = cNum;
                for (int[] d2 : dir) {
                    int nnr = nr + d2[0];
                    int nnc = nc + d2[1];
                    if (check(nnr, nnc) || map[nnr][nnc] == 0 || map[nr][nc] == map[nnr][nnc] || find(map[nr][nc]) == find(map[nnr][nnc]))
                        continue;
                    union(map[nr][nc], map[nnr][nnc]);
                    K--;
                    if (K == 1) break;
                }
            }
        }
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    private static void union(int a, int b) {
        int A = find(a);
        int B = find(b);
        if (A != B) parent[B] = A;
    }

    private static int find(int a) {
        return parent[a] == a ? a : (parent[a] = find(parent[a]));
    }
}
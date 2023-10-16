import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, blockCnt;
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static BlockGroup bg;
    static List<int[]> tmpList;
    static Queue<int[]> queue;
    static boolean[][] visit, v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        blockCnt = 0;
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] > 0) blockCnt++;
            }
        }
        int score = 0;
        while (blockCnt > 0) {
            bg = new BlockGroup(0, new ArrayList<>());
            visit = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (visit[r][c] || map[r][c] <= 0) continue;
                    findBlock(r, c, map[r][c]);
                }
            }
            if (bg.list.size() == 1) break;
            for (int[] block : bg.list) {
                if (map[block[0]][block[1]] != 0) blockCnt--;
                map[block[0]][block[1]] = -2;
            }
            score += Math.pow(bg.list.size(), 2);
            gravity();
            turnMap();
            gravity();
        }
        System.out.println(score);
    }

    private static void findBlock(int sr, int sc, int type) {
        tmpList = new ArrayList<>();
        queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        v = new boolean[N][N];
        v[sr][sc] = true;
        int rainbowCnt = 0;
        while (!queue.isEmpty()) {
            int[] block = queue.poll();
            tmpList.add(block);
            if (map[block[0]][block[1]] == 0) rainbowCnt++;
            else visit[block[0]][block[1]] = true;
            for (int[] d : dir) {
                int nr = block[0] + d[0];
                int nc = block[1] + d[1];
                if (check(nr, nc) || v[nr][nc] || (map[nr][nc] != type && map[nr][nc] != 0)) continue;
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
            }
        }
        if (bg.list.size() < tmpList.size() || (bg.list.size() == tmpList.size() && bg.rainbowCnt <= rainbowCnt))
            bg = new BlockGroup(rainbowCnt, tmpList);
    }

    private static void gravity() {
        for (int c = 0; c < N; c++) {
            for (int r = N - 2; r >= 0; r--) {
                if (map[r][c] < 0) continue;
                for (int nr = r + 1; nr <= N; nr++) {
                    if (nr == N || map[nr][c] != -2) {
                        if (map[nr - 1][c] == -2) {
                            map[nr - 1][c] = map[r][c];
                            map[r][c] = -2;
                        }
                        break;
                    }
                }
            }
        }
    }

    private static void turnMap() {
        int[][] tmpMap = new int[N][N];
        for (int c = N - 1; c >= 0; c--) {
            for (int r = 0; r < N; r++) {
                tmpMap[N - 1 - c][r] = map[r][c];
            }
        }
        map = tmpMap;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    private static class BlockGroup {
        int rainbowCnt;
        List<int[]> list;

        private BlockGroup(int rainbowCnt, List<int[]> list) {
            this.rainbowCnt = rainbowCnt;
            this.list = list;
        }
    }
}
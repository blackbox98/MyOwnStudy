import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static final int N = 4;
    static int answer = 0;
    static int[][] dir = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}}; // ↑, ↖, ←, ↙, ↓, ↘, →, ↗

    private static class Point {
        int r;
        int c;
        int d;

        private Point(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] fishes = new int[N][N];
        Map<Integer, Point> map = new HashMap<>();
        int num, d;
        int r = 0;
        int c = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                num = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken()) - 1;
                fishes[r][c] = num;
                map.put(num, new Point(r, c, d));
                if (++c == N) {
                    r++;
                    c = 0;
                }
            }
        }
        eatingFish(fishes, map, 0, 0, 0, 1);
        System.out.println(answer);
    }

    private static void eatingFish(int[][] fishes, Map<Integer, Point> map, int sr, int sc, int cnt, int level) {
        int[][] curFishes = copyMap(fishes);
        Map<Integer, Point> copyMap = new HashMap<>(map);
        cnt += curFishes[sr][sc];
        answer = Math.max(answer, cnt);
        Point p = copyMap.get(curFishes[sr][sc]);
        copyMap.put(curFishes[sr][sc], null);
        curFishes[sr][sc] = 0;
        copyMap.put(0, new Point(sr, sc, p.d));
        Point fish;
        int pr, pc, pd, nr, nc, tn;
        for (int n = 1; n <= 16; n++) {
            fish = copyMap.get(n);
            if (fish == null) continue;
            pr = fish.r;
            pc = fish.c;
            pd = fish.d;
            for (int i = 0; i < 8; i++) {
                nr = pr + dir[pd][0];
                nc = pc + dir[pd][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && curFishes[nr][nc] != 0) {
                    tn = curFishes[nr][nc];
                    if (tn != -1) {
                        copyMap.put(tn, new Point(pr, pc, copyMap.get(tn).d));
                    }
                    curFishes[pr][pc] = tn;
                    curFishes[nr][nc] = n;
                    copyMap.put(n, new Point(nr, nc, pd));
                    break;
                }
                pd = (pd + 1) % 8;
            }
        }
        curFishes[sr][sc] = -1;
        int k = 1;
        while (true) {
            nr = sr + dir[p.d][0] * k;
            nc = sc + dir[p.d][1] * k++;
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) break;
            if (curFishes[nr][nc] == -1) continue;
            eatingFish(curFishes, copyMap, nr, nc, cnt, level + 1);
        }
    }

    private static int[][] copyMap(int[][] map) {
        int[][] tmp = new int[N][N];
        for (int r = 0; r < N; r++) {
            tmp[r] = map[r].clone();
        }
        return tmp;
    }
}
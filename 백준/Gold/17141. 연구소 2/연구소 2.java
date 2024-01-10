import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static List<Virus> list;
    static Queue<int[]> queue;

    private static class Virus {
        int r;
        int c;
        int[][] diffMap;

        private Virus(int r, int c) {
            this.r = r;
            this.c = c;
            this.diffMap = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(this.diffMap[i], Integer.MAX_VALUE);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        list = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 2) {
                    list.add(new Virus(r, c));
                    map[r][c] = 0;
                }
            }
        }
        for (Virus v : list) {
            diffusion(v);
        }
        answer = Integer.MAX_VALUE;
        combination(new int[M], 0, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void diffusion(Virus v) {
        queue = new LinkedList<>();
        queue.offer(new int[]{v.r, v.c, 0});
        v.diffMap[v.r][v.c] = 0;
        while (!queue.isEmpty()) {
            int[] virus = queue.poll();
            int nd = virus[2] + 1;
            for (int[] d : dir) {
                int nr = virus[0] + d[0];
                int nc = virus[1] + d[1];
                if (check(nr, nc) || map[nr][nc] == 1 || v.diffMap[nr][nc] <= nd) continue;
                queue.offer(new int[]{nr, nc, nd});
                v.diffMap[nr][nc] = nd;
            }
        }
    }

    private static void combination(int[] arr, int k, int idx) {
        if (k == M) {
            int max = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] == 1) continue;
                    int min = Integer.MAX_VALUE;
                    for (int n : arr) {
                        min = Math.min(min, list.get(n).diffMap[r][c]);
                    }
                    if (min == Integer.MAX_VALUE) return;
                    max = Math.max(max, min);
                }
            }
            answer = Math.min(answer, max);
            return;
        }
        for (int i = idx; i < list.size(); i++) {
            arr[k] = i;
            combination(arr, k + 1, i + 1);
        }
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}
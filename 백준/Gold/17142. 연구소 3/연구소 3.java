import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, answer;
    static List<Virus> list;
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 2) list.add(new Virus(r, c));
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
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{v.r, v.c, 0});
        v.diffMap[v.r][v.c] = 0;
        while (!queue.isEmpty()) {
            int[] virus = queue.poll();
            int nd = virus[2] + 1;
            for (int[] d : dir) {
                int nr = virus[0] + d[0];
                int nc = virus[1] + d[1];
                if (check(nr, nc) || map[nr][nc] == 1) continue;
                if (v.diffMap[nr][nc] == 0 || v.diffMap[nr][nc] > nd) {
                    v.diffMap[nr][nc] = nd;
                    queue.offer(new int[]{nr, nc, nd});
                }
            }
        }
    }

    private static void combination(int[] arr, int depth, int idx) {
        if (depth == M) {
            int max = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] != 0) continue;
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
            arr[depth] = i;
            combination(arr, depth + 1, i + 1);
        }
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

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
}
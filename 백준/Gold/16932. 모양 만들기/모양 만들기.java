import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    static boolean[][] v;
    static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        v = new boolean[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        map = new HashMap<>();
        int num = 2;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (arr[r][c] == 0 || v[r][c]) continue;
                bfs(r, c, arr[r][c], num++);
            }
        }
        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (arr[r][c] == 0) answer = Math.max(answer, checkAround(r, c));
            }
        }
        System.out.println(answer);
    }

    private static void bfs(int sr, int sc, int sn, int num) {
        int cnt = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        v[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int pr = point[0];
            int pc = point[1];
            arr[pr][pc] = num;
            for (int[] d : dir) {
                int nr = pr + d[0];
                int nc = pc + d[1];
                if (check(nr, nc) || v[nr][nc] || arr[nr][nc] != sn) continue;
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
                cnt++;
            }
        }
        map.put(num, cnt);
    }

    private static int checkAround(int r, int c) {
        int sum = 1;
        Set<Integer> set = new HashSet<>();
        for (int[] d : dir) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (check(nr, nc) || arr[nr][nc] == 0 || set.contains(arr[nr][nc])) continue;
            set.add(arr[nr][nc]);
            sum += map.get(arr[nr][nc]);
        }
        return sum;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}
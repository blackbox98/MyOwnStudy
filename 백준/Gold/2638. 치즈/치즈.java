import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int answer = 0, cnt = 0, N, M;
    static int[][] cheese;
    static boolean[][] v;
    static List<int[]> meltingList;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cheese = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                cheese[r][c] = Integer.parseInt(st.nextToken());
                if (cheese[r][c] == 1) cnt++;
            }
        }
        checkAir(0, 0);
        while (cnt > 0) {
            meltingList = new ArrayList<>();
            v = new boolean[N][M];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (!v[r][c] && cheese[r][c] == 1 && checkOutsideAir(r, c)) {
                        melting(r, c);
                    }
                }
            }
            for (int[] meltingCheese : meltingList) {
                int mr = meltingCheese[0];
                int mc = meltingCheese[1];
                if (cheese[mr][mc] != 2) checkAir(mr, mc);
            }
            cnt -= meltingList.size();
            answer++;
        }
        System.out.println(answer);
    }

    private static void checkAir(int sr, int sc) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        cheese[sr][sc] = 2;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            for (int[] d : dir) {
                int nr = point[0] + d[0];
                int nc = point[1] + d[1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && cheese[nr][nc] == 0) {
                    queue.offer(new int[]{nr, nc});
                    cheese[nr][nc] = 2;
                }
            }
        }
    }

    private static boolean checkOutsideAir(int sr, int sc) {
        int airCnt = 0;
        for (int[] d : dir) {
            int nr = sr + d[0];
            int nc = sc + d[1];
            if (nr >= 0 && nr < N && nc >= 0 && nc < M && cheese[nr][nc] == 2) airCnt++;
        }
        return airCnt >= 2;
    }

    private static void melting(int sr, int sc) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        meltingList.add(new int[]{sr, sc});
        cheese[sr][sc] = 0;
        v[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            for (int[] d1 : dir) {
                int nr = point[0] + d1[0];
                int nc = point[1] + d1[1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && !v[nr][nc] && cheese[nr][nc] == 1) {
                    queue.offer(new int[]{nr, nc});
                    v[nr][nc] = true;
                    if (checkOutsideAir(nr, nc)) {
                        meltingList.add(new int[]{nr, nc});
                        cheese[nr][nc] = 0;
                    }
                }
            }
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, numCnt;
    static List<Integer>[] listArr;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        listArr = new List[N + 1];
        for (int r = 1; r <= N; r++) {
            listArr[r] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                listArr[r].add(Integer.parseInt(st.nextToken()));
            }
        }
        numCnt = N * M;
        boolean flag;
        while (T-- > 0 && numCnt > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            if (k > (M + 1) / 2) {
                d = (d + 1) % 2;
                k = M - k;
            }
            int n = 1;
            while (x * n <= N) {
                turn(x * n++, d, k);
            }
            flag = false;
            for (int r = 1; r <= N; r++) {
                for (int c = 0; c < M; c++) {
                    if (listArr[r].get(c) > 0 && eraseAdjNum(r, c)) flag = true;
                }
            }
            if (!flag) everage();
        }
        int answer = 0;
        for (int r = 1; r <= N; r++) {
            for (int c = 0; c < M; c++) {
                answer += listArr[r].get(c);
            }
        }
        System.out.println(answer);
    }

    private static void turn(int x, int d, int k) {
        while (k-- > 0) {
            if (d == 0) listArr[x].add(0, listArr[x].remove(M - 1));
            else listArr[x].add(listArr[x].remove(0));
        }
    }

    private static boolean eraseAdjNum(int sr, int sc) {
        Queue<int[]> eraseQueue = new LinkedList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        boolean[][] v = new boolean[N + 1][M];
        v[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] target = queue.poll();
            eraseQueue.offer(target);
            for (int[] d : dir) {
                int nr = target[0] + d[0];
                int nc = target[1] + d[1];
                if (nc < 0) nc = M - 1;
                else if (nc == M) nc = 0;
                if (nr < 1 || nr > N || v[nr][nc] || !listArr[target[0]].get(target[1]).equals(listArr[nr].get(nc)))
                    continue;
                queue.offer(new int[]{nr, nc});
                v[nr][nc] = true;
            }
        }
        if (eraseQueue.size() > 1) {
            for (int[] target : eraseQueue) {
                listArr[target[0]].set(target[1], 0);
            }
            numCnt -= eraseQueue.size();
        }
        return eraseQueue.size() > 1;
    }

    private static void everage() {
        double sum = 0;
        int cnt = 0;
        for (int r = 1; r <= N; r++) {
            for (int c = 0; c < M; c++) {
                if (listArr[r].get(c) > 0) {
                    sum += listArr[r].get(c);
                    cnt++;
                }
            }
        }
        double ever = sum / cnt;
        for (int r = 1; r <= N; r++) {
            for (int c = 0; c < M; c++) {
                if (listArr[r].get(c) == 0) continue;
                if (listArr[r].get(c) > ever) listArr[r].set(c, listArr[r].get(c) - 1);
                else if (listArr[r].get(c) < ever) listArr[r].set(c, listArr[r].get(c) + 1);
            }
        }
    }
}
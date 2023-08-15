import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int answer;
    static final int N = 5, princessNum = 7;
    static char[][] map;
    static int[][] indexMap;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[N][N];
        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
        }
        indexMap = new int[25][2];
        for (int i = 0; i < N * N; i++) {
            indexMap[i][0] = i / N;
            indexMap[i][1] = i % N;
        }
        answer = 0;
        combination(new int[princessNum], princessNum, 0, 0);
        System.out.println(answer);
    }

    private static void combination(int[] arr, int total, int idx, int num) {
        if (total == 0) {
            if (bfs(arr)) answer++;
            return;
        }
        if (num == 25) return;
        arr[idx] = num;
        combination(arr, total - 1, idx + 1, num + 1);
        combination(arr, total, idx, num + 1);
    }

    private static boolean bfs(int[] arr) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(arr[0]);
        boolean[] v = new boolean[princessNum];
        v[0] = true;
        int cnt = 1;
        int sCnt = 0;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (map[indexMap[now][0]][indexMap[now][1]] == 'S') sCnt++;
            for (int[] d : dir) {
                int nr = indexMap[now][0] + d[0];
                int nc = indexMap[now][1] + d[1];
                for (int i = 0; i < princessNum; i++) {
                    if (!v[i] && nr == indexMap[arr[i]][0] && nc == indexMap[arr[i]][1]) {
                        v[i] = true;
                        queue.offer(arr[i]);
                        cnt++;
                    }
                }
            }
        }
        return cnt == princessNum && sCnt >= 4;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] start = new int[]{0, 0, 0};
    static int[] end = new int[]{10000, 10000};
    static int[][] airFields;
    static Queue<int[]> queue;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        airFields = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            airFields[i] = new int[]{x, y};
        }
        int left = 0;
        int right = getFuel(start, end);
        int answer = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isMinFuelTank(mid)) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else left = mid + 1;
        }
        System.out.println(answer);
    }

    private static int getFuel(int[] start, int[] end) {
        double fuel = Math.sqrt(Math.pow(end[0] - start[0], 2) + Math.pow(end[1] - start[1], 2));
        return fuel / 10 == (int) (fuel / 10) ? (int) fuel / 10 : (int) fuel / 10 + 1;
    }

    private static boolean isMinFuelTank(int mid) {
        queue = new LinkedList<>();
        queue.offer(start);
        v = new boolean[n];
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (getFuel(cur, end) <= mid) return true;
            if (cur[2] == k) continue;
            for (int i = 0; i < n; i++) {
                if (v[i] || getFuel(cur, airFields[i]) > mid) continue;
                queue.offer(new int[]{airFields[i][0], airFields[i][1], cur[2] + 1});
                v[i] = true;
            }
        }
        return false;
    }
}
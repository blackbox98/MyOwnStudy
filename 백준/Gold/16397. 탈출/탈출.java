import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int limit = 99999;
    static Queue<int[]> queue;
    static Set<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        System.out.println(bfs(N, G, T));
    }

    private static String bfs(int N, int G, int T) {
        int cnt = Integer.MAX_VALUE;
        queue = new LinkedList<>();
        queue.offer(new int[]{N, 0, T});
        set = new HashSet<>();
        set.add(N);
        while (!queue.isEmpty()) {
            int[] num = queue.poll();
            if (num[0] == G) {
                cnt = Math.min(cnt, num[1]);
                continue;
            }
            if (num[1] == T) continue;
            if (num[0] + 1 <= limit) checkNextN(num[0] + 1, num[1] + 1);
            if (num[0] * 2 <= limit) checkNextN(pressB(num[0] * 2), num[1] + 1);
        }
        return cnt == Integer.MAX_VALUE ? "ANG" : "" + cnt;
    }

    private static void checkNextN(int n, int cnt) {
        if (!set.contains(n)) {
            queue.offer(new int[]{n, cnt});
            set.add(n);
        }
    }

    private static int pressB(int n) {
        int k = (int) Math.pow(10, ("" + n).length() - 1);
        return n - k;
    }
}
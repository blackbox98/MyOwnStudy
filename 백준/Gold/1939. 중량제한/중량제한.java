import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, start, end;
    static List<Bridge>[] bridge;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        bridge = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            bridge[i] = new ArrayList<>();
        }
        int M = Integer.parseInt(st.nextToken());
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            min = Math.min(min, C);
            max = Math.max(max, C);
            bridge[A].add(new Bridge(B, C));
            bridge[B].add(new Bridge(A, C));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        int answer = 0;
        int mid;
        while (min <= max) {
            mid = (min + max) / 2;
            if (findBridge(mid)) {
                min = mid + 1;
                answer = mid;
            } else max = mid - 1;
        }
        System.out.println(answer);
    }

    private static boolean findBridge(int weight) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        boolean[] v = new boolean[N + 1];
        v[start] = true;
        while (!queue.isEmpty()) {
            int s = queue.poll();
            if (s == end) return true;
            for (int i = 0; i < bridge[s].size(); i++) {
                Bridge brg = bridge[s].get(i);
                if (v[brg.e] || brg.w < weight) continue;
                v[brg.e] = true;
                queue.offer(brg.e);
            }
        }
        return false;
    }

    private static class Bridge {
        int e;
        int w;

        private Bridge(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
}
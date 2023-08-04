import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, P, K;
    static int[] dist;
    static List<Cable>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dist = new int[N + 1];
        arr = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }
        int right = 0;
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            arr[s].add(new Cable(e, cost));
            arr[e].add(new Cable(s, cost));
            right = Math.max(right, cost);
        }
        int answer = -1;
        int left = 0;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (dijkstra(mid)) {
                answer = mid;
                right = mid - 1;
            } else left = mid + 1;
        }
        System.out.println(answer);
    }

    private static boolean dijkstra(int target) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        PriorityQueue<Cable> pq = new PriorityQueue<>();
        pq.offer(new Cable(1, 0));
        while (!pq.isEmpty()) {
            Cable c = pq.poll();
            if (dist[c.e] < c.cost) continue;
            for (Cable cable : arr[c.e]) {
                int nowCost = c.cost;
                if (cable.cost > target) nowCost++;
                if (dist[cable.e] > nowCost) {
                    dist[cable.e] = nowCost;
                    pq.offer(new Cable(cable.e, nowCost));
                }
            }
        }
        return dist[N] <= K;
    }

    private static class Cable implements Comparable<Cable> {
        int e;
        int cost;

        private Cable(int e, int cost) {
            this.e = e;
            this.cost = cost;
        }

        @Override
        public int compareTo(Cable o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
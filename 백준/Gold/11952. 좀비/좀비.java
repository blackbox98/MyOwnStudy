import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final long INF = Long.MAX_VALUE;
    static int N, S, p, q;
    static int[] diminished;
    static List<Integer>[] list;
    static Queue<int[]> queue;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        // 0 : 안전한 도시, 1 : 위험한 도시, 2 : 점령당한 도시
        diminished = new int[N + 1];
        for (int i = 0; i < K; i++) {
            diminished[Integer.parseInt(br.readLine())] = 2;
        }
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list[s].add(e);
            list[e].add(s);
        }
        queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (diminished[i] == 2) checkNotSafe(i);
        }
        System.out.println(dijkstra());
    }

    private static void checkNotSafe(int s) {
        queue.offer(new int[]{s, 0});
        v = new boolean[N + 1];
        v[s] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[1] == S) continue;
            for (int next : list[cur[0]]) {
                if (diminished[next] == 2 || v[next]) continue;
                queue.offer(new int[]{next, cur[1] + 1});
                v[next] = true;
                diminished[next] = 1;
            }
        }
    }

    private static long dijkstra() {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0L;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o[1]));
        pq.offer(new long[]{1, 0L});
        v = new boolean[N + 1];
        while (!pq.isEmpty()) {
            int curIdx = (int) pq.poll()[0];
            if (curIdx == N) break;
            if (v[curIdx]) continue;
            v[curIdx] = true;
            for (int next : list[curIdx]) {
                if (diminished[next] == 2 || v[next]) continue;
                int cost = diminished[next] == 0 ? p : q;
                if (dist[next] <= dist[curIdx] + cost) continue;
                dist[next] = dist[curIdx] + cost;
                pq.offer(new long[]{next, dist[next]});
            }
        }
        return dist[N] - (diminished[N] == 0 ? p : q);
    }
}
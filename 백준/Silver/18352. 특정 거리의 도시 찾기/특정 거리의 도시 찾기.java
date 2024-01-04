import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        List<Integer>[] list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list[s].add(e);
        }
        System.out.println(dijkstra(list, N, X, K));
    }

    private static StringBuilder dijkstra(List<Integer>[] list, int N, int X, int K) {
        StringBuilder sb = new StringBuilder();
        int INF = 987654321;
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[X] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{X, 0});
        boolean[] v = new boolean[N + 1];
        while (!pq.isEmpty()) {
            int cur = pq.poll()[0];
            if (v[cur]) continue;
            v[cur] = true;
            for (int next : list[cur]) {
                if (v[next] || dist[next] <= dist[cur] + 1) continue;
                dist[next] = dist[cur] + 1;
                pq.offer(new int[]{next, dist[next]});
            }
        }
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) sb.append(i).append("\n");
        }
        return sb.length() == 0 ? sb.append(-1) : sb;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = 987654321;
    static int N, M;
    static List<Shed>[] list;
    static List<Shed> shedList;

    private static class Shed implements Comparable<Shed> {
        int idx;
        int dist;

        private Shed(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Shed o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list[s].add(new Shed(e, 1));
            list[e].add(new Shed(s, 1));
        }
        shedList = new ArrayList<>();
        dijkstra();
        shedList.sort((o1, o2) -> {
            if (o1.dist == o2.dist) return o1.idx - o2.idx;
            else return o2.dist - o1.dist;
        });
        int[] answer = new int[3];
        answer[0] = shedList.get(0).idx;
        answer[1] = shedList.get(0).dist;
        for (Shed shed : shedList) {
            if (shed.dist == answer[1]) answer[2]++;
        }
        System.out.printf("%d %d %d\n", answer[0], answer[1], answer[2]);
    }

    private static void dijkstra() {
        PriorityQueue<Shed> pq = new PriorityQueue<>();
        pq.offer(new Shed(1, 0));
        boolean[] v = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        while (!pq.isEmpty()) {
            int curIdx = pq.poll().idx;
            if (v[curIdx]) continue;
            v[curIdx] = true;
            for (Shed next : list[curIdx]) {
                if (dist[next.idx] <= dist[curIdx] + next.dist) continue;
                dist[next.idx] = dist[curIdx] + next.dist;
                pq.offer(new Shed(next.idx, dist[next.idx]));
            }
        }
        for (int idx = 1; idx <= N; idx++) {
            shedList.add(new Shed(idx, dist[idx]));
        }
    }
}
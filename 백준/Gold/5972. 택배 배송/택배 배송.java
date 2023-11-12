import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static List<Node>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            Node node = new Node(e, w);
            if (adjList[s].contains(node)) {
                int idx = adjList[s].indexOf(node);
                node.w = Math.min(node.w, adjList[s].get(idx).w);
                adjList[s].set(idx, node);
            } else adjList[s].add(node);
            node = new Node(s, w);
            if (adjList[e].contains(node)) {
                int idx = adjList[e].indexOf(node);
                node.w = Math.min(node.w, adjList[e].get(idx).w);
                adjList[e].set(idx, node);
            } else adjList[e].add(node);
        }
        System.out.println(dijkstra());
    }

    private static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        boolean[] v = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        while (!pq.isEmpty()) {
            int cur = pq.poll().e;
            if (v[cur]) continue;
            v[cur] = true;
            for (Node next : adjList[cur]) {
                if (dist[next.e] > dist[cur] + next.w) {
                    dist[next.e] = dist[cur] + next.w;
                    pq.offer(new Node(next.e, dist[next.e]));
                }
            }
        }
        return dist[N] == Integer.MAX_VALUE ? 0 : dist[N];
    }

    private static class Node implements Comparable<Node> {
        int e;
        int w;

        private Node(int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return e == node.e;
        }

        @Override
        public int hashCode() {
            return Objects.hash(e);
        }
    }
}
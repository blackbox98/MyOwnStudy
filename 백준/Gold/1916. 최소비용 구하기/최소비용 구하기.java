import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<Node>[] adjList;

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
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[s].add(new Node(e, w));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        System.out.println(dijkstra(start, end));
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int curVertex = curNode.e;
            int curDist = curNode.w;
            if (dist[curVertex] < curDist) continue;
            for (Node next : adjList[curVertex]) {
                if (dist[next.e] > dist[curVertex] + next.w) {
                    dist[next.e] = dist[curVertex] + next.w;
                    pq.offer(new Node(next.e, dist[next.e]));
                }
            }
        }
        return dist[end];
    }
}
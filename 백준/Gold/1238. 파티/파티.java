import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, X;
    
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
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        List<Node>[] adjList, reverseAdjList;
        adjList = new List[N + 1];
        reverseAdjList = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
            reverseAdjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[s].add(new Node(e, w));
            reverseAdjList[e].add(new Node(s, w));
        }
        int answer = 0;
        int[] adjDist = dijkstra(adjList);
        int[] reverseAdjDist = dijkstra(reverseAdjList);
        for (int i = 1; i <= N; i++) {
            if (i == X) continue;
            answer = Math.max(answer, adjDist[i] + reverseAdjDist[i]);
        }
        System.out.println(answer);
    }
    
    private static int[] dijkstra(List<Node>[] adjList) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(X, 0));
        boolean[] v = new boolean[N + 1];
        while (!pq.isEmpty()) {
            int curVertex = pq.poll().e;
            if (v[curVertex]) continue;
            v[curVertex] = true;
            for (Node nextNode : adjList[curVertex]) {
                if (!v[nextNode.e] && dist[nextNode.e] > dist[curVertex] + nextNode.w) {
                    dist[nextNode.e] = dist[curVertex] + nextNode.w;
                    pq.offer(new Node(nextNode.e, dist[nextNode.e]));
                }
            }
        }
        return dist;
    }
}
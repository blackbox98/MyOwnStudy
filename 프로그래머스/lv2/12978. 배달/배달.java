import java.util.*;

class Solution {
    static List<Edge>[] list;
    static boolean[] v;
    static int[] distMap;
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        list = new List[N + 1];
        v = new boolean[N + 1];
        distMap = new int[N + 1];
        Arrays.fill(distMap, Integer.MAX_VALUE);
        
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < road.length; i++) {
            int from = road[i][0];
            int to = road[i][1];
            int dist = road[i][2];
            list[from].add(new Edge(to, dist));
            list[to].add(new Edge(from, dist));
        }

        dijkstra(1);
        for (int d : distMap) {
            if (d <= K) answer++;
        }
        return answer;
    }
    
    private static void dijkstra(int start) {
        Queue<Edge> queue = new PriorityQueue<>();
        queue.offer(new Edge(start, 0));
        distMap[start] = 0;

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            int to = edge.to;
            if (!v[to]) {
                v[to] = true;
                for (Edge adj : list[to]) {
                    if (distMap[adj.to] >= distMap[to] + adj.dist) {
                        distMap[adj.to] = distMap[to] + adj.dist;
                        queue.offer(new Edge(adj.to, distMap[adj.to]));
                    }
                }
            }
        }
    }
    
    private static class Edge implements Comparable<Edge> {
        int to;
        int dist;

        public Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
}
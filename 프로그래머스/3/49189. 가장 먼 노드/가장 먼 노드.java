import java.util.*;

class Solution {
    List<Node>[] list;
    
    public class Node implements Comparable<Node> {
        int e;
        int w;
        
        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
    
    public int solution(int n, int[][] edge) {
        list = new List[n + 1];
        for (int i = 1; i <= n; i++) list[i] = new ArrayList<>();
        for (int[] edgeInfo : edge) {
            list[edgeInfo[0]].add(new Node(edgeInfo[1], 1));
            list[edgeInfo[1]].add(new Node(edgeInfo[0], 1));
        }
        return dijkstra(n, 1);
    }
    
    public int dijkstra(int n, int start) {
        int INF = 987654321;
        Integer[] dist = new Integer[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        boolean[] v = new boolean[n + 1];
        while (!pq.isEmpty()) {
            int cur = pq.poll().e;
            if (v[cur]) continue;
            v[cur] = true;
            for (Node next : list[cur]) {
                if (dist[next.e] > dist[cur] + next.w) {
                    dist[next.e] = dist[cur] + next.w;
                    pq.offer(new Node(next.e, dist[next.e]));
                }
            }
        }
        Arrays.sort(dist, Collections.reverseOrder());
        int max = dist[0];
        int answer = 0;
        for (int d : dist) {
            if (d == INF) continue;
            else if (max == INF) max = d;
            if (d == max) answer++;
            else break;
        }
        return answer;
    }
}
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Node>[] graph;
    static int[] times;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph = new List[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }
            times = new int[n + 1];
            Arrays.fill(times, INF);
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                graph[b].add(new Node(a, s));
            }
            dijkstra(c);
            int totalCom = 0;
            int totalTime = 0;
            for (int i = 1; i <= n; i++) {
                if (times[i] == INF) continue;
                totalCom++;
                totalTime = Math.max(totalTime, times[i]);
            }
            System.out.printf("%d %d\n", totalCom, totalTime);
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));
        times[start] = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int end = node.e;
            int time = node.time;
            if (times[end] < time) continue;
            for (int i = 0; i < graph[end].size(); i++) {
                int estimatedTime = times[end] + graph[end].get(i).time;
                if (estimatedTime < times[graph[end].get(i).e]) {
                    times[graph[end].get(i).e] = estimatedTime;
                    queue.offer(new Node(graph[end].get(i).e, estimatedTime));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int e;
        int time;

        private Node(int e, int time) {
            this.e = e;
            this.time = time;
        }

        @Override
        public int compareTo(Node node) {
            return this.time - node.time;
        }
    }
}
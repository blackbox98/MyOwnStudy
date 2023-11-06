import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 1000000000;
    static int V, E;
    static int[][] graph;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new int[V + 1][V + 1];
        for (int i = 1; i <= V; i++) {
            Arrays.fill(graph[i], INF);
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s][e] = w;
        }
        for (int g = 1; g <= V; g++) {
            for (int s = 1; s <= V; s++) {
                if (g == s) continue;
                for (int e = 1; e <= V; e++) {
                    if (g == e || s == e) continue;
                    graph[s][e] = Math.min(graph[s][e], graph[s][g] + graph[g][e]);
                }
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int s = 1; s <= V; s++) {
            for (int e = s + 1; e <= V; e++) {
                if (graph[s][e] != INF && graph[e][s] != INF) answer = Math.min(answer, graph[s][e] + graph[e][s]);
            }
        }
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}
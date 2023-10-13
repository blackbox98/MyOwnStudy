import java.io.*;
import java.util.*;

public class Main {
    static int N, answer;
    static int[][] edges;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        edges = new int[N][N];
        for (int s = 0; s < N; s++) {
            st = new StringTokenizer(br.readLine());
            for (int e = 0; e < N; e++) {
                edges[s][e] = Integer.parseInt(st.nextToken());
            }
        }
        for (int k = 0; k < N; k++) {
            for (int s = 0; s < N; s++) {
                if (s == k) continue;
                for (int e = 0; e < N; e++) {
                    if (s == e || e == k) continue;
                    edges[s][e] = Math.min(edges[s][e], edges[s][k] + edges[k][e]);
                }
            }
        }
        answer = Integer.MAX_VALUE;
        boolean[] v = new boolean[N];
        v[K] = true;
        shortPath(v, K, 0, 0);
        System.out.println(answer);
    }
    
    private static void shortPath(boolean[] v, int s, int depth, int dist) {
        if (depth == N - 1) {
            answer = Math.min(answer, dist);
            return;
        }
        
        for (int i = 0; i < N; i++) {
            if (v[i]) continue;
            v[i] = true;
            shortPath(v, i, depth + 1, dist + edges[s][i]);
            v[i] = false;
        }
    }
}
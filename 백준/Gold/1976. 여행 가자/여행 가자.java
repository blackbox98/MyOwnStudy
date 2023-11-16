import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 1000000000;
    static int N;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        dist = new int[N][N];
        StringTokenizer st;
        for (int s = 0; s < N; s++) {
            st = new StringTokenizer(br.readLine());
            for (int e = 0; e < N; e++) {
                dist[s][e] = Integer.parseInt(st.nextToken());
                if (dist[s][e] == 0) dist[s][e] = INF;
            }
        }
        floyd_warshall();
        boolean answer = true;
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 1; i < M; i++) {
            int end = Integer.parseInt(st.nextToken()) - 1;
            if (start != end && dist[start][end] == INF) {
                answer = false;
                break;
            }
            start = end;
        }
        System.out.println(answer ? "YES" : "NO");
    }

    private static void floyd_warshall() {
        for (int k = 0; k < N; k++) {
            for (int s = 0; s < N; s++) {
                for (int e = 0; e < N; e++) {
                    dist[s][e] = Math.min(dist[s][e], dist[s][k] + dist[k][e]);
                }
            }
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] dist = new int[N + 1][N + 1];
        int INF = 987654321;
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            dist[s][e] = 1;
            dist[e][s] = 1;
        }
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if (k == i) continue;
                for (int j = 1; j <= N; j++) {
                    if (k == j || i == j) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int[][] kevinBacon = new int[N][2];
        for (int i = 1; i <= N; i++) {
            kevinBacon[i - 1][0] = i;
            for (int j = 1; j <= N; j++) {
                if (dist[i][j] != INF) kevinBacon[i - 1][1] += dist[i][j];
            }
        }
        Arrays.sort(kevinBacon, (o1, o2) -> {
            if (o1[1] == o2[1]) return o1[0] - o2[0];
            else return o1[1] - o2[1];
        });
        System.out.println(kevinBacon[0][0]);
    }
}
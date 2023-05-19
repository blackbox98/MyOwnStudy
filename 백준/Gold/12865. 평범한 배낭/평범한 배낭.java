import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] thing = new int[N + 1][2];
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            thing[i][0] = Integer.parseInt(st.nextToken());
            thing[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int v = 1; v <= N; v++) {
            for (int w = 1; w <= K; w++) {
                dp[v][w] = dp[v - 1][w];
                if(w >= thing[v][0]) dp[v][w] = Math.max(dp[v - 1][w], dp[v - 1][w - thing[v][0]] + thing[v][1]);
            }
        }
        System.out.println(dp[N][K]);
    }
}

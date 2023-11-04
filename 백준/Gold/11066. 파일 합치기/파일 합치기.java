import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int K = Integer.parseInt(br.readLine());
            int[] files = new int[K + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                files[i] = files[i - 1] + Integer.parseInt(st.nextToken());
            }
            int[][] dp = new int[K + 1][K + 1];
            for (int g = 1; g < K; g++) {
                for (int s = 1; s + g <= K; s++) {
                    int e = s + g;
                    dp[s][e] = Integer.MAX_VALUE;
                    for (int m = s; m < e; m++) {
                        dp[s][e] = Math.min(dp[s][e], dp[s][m] + dp[m + 1][e] + files[e] - files[s - 1]);
                    }
                }
            }
            sb.append(dp[1][K]).append("\n");
        }
        System.out.println(sb);
    }
}
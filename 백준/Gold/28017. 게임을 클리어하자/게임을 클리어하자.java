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
        int M = Integer.parseInt(st.nextToken());
        int[][] weapons = new int[N][M];
        for (int i = 0; i < N; i++) {
            int j = 0;
            for (String weapon : br.readLine().split(" ")) weapons[i][j++] = Integer.parseInt(weapon);
        }
        int answer = Integer.MAX_VALUE;
        int[][] dp = new int[N][M];
        dp[0] = weapons[0].clone();
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < M; k++) {
                    if (j == k) continue;
                    if (min > dp[i - 1][k]) {
                        min = dp[i - 1][k];
                    }
                }
                dp[i][j] = min + weapons[i][j];
            }
        }
        for (int i = 0; i < M; i++) {
            answer = Math.min(answer, dp[N - 1][i]);
        }
        System.out.println(answer);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] weights;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weights = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }
        dp = new boolean[N + 1][40001];
        dfs(0, 0);
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            sb.append(dp[N][Integer.parseInt(st.nextToken())] ? "Y " : "N ");
        }
        System.out.println(sb);
    }

    private static void dfs(int wCnt, int curWeight) {
        if (dp[wCnt][curWeight]) return;
        dp[wCnt][curWeight] = true;
        if (wCnt == N) return;
        dfs(wCnt + 1, curWeight);
        dfs(wCnt + 1, curWeight + weights[wCnt]);
        dfs(wCnt + 1, Math.abs(curWeight - weights[wCnt]));
    }
}
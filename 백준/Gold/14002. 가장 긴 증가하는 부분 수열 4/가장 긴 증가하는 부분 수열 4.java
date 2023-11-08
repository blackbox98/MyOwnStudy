import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int answer = 0;
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (nums[i] < nums[j]) dp[j] = Math.max(dp[j], dp[i] + 1);
            }
            answer = Math.max(answer, dp[i]);
        }
        int[] LIS = new int[answer];
        int max = answer;
        for (int i = N - 1; i >= 0; i--) {
            if (dp[i] == max) {
                LIS[--max] = nums[i];
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(answer).append("\n");
        for (int i = 0; i < answer; i++) {
            sb.append(LIS[i]).append(" ");
        }
        System.out.println(sb);
    }
}
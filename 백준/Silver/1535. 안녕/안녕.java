import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] nums = new int[N][2];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int strength = 0;
        int joy = 0;
        for (int i = 0; i < N; i++) {
            nums[i][0] = Integer.parseInt(st1.nextToken());
            nums[i][1] = Integer.parseInt(st2.nextToken());
            strength += nums[i][0];
            joy += nums[i][1];
        }
        if (strength >= 100) {
            int[] dp = new int[100];
            for (int i = 0; i < N; i++) {
                for (int j = 99; j >= nums[i][0]; j--) {
                    dp[j] = Math.max(dp[j - nums[i][0]] + nums[i][1], dp[j]);
                }
            }
            joy = dp[99];
        }
        System.out.println(joy);
    }
}
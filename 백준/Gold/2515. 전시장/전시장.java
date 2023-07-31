import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));
        int[][] dp = new int[N + 1][2];
        dp[1][1] = arr[1][1];
        int left, right, target, mid;
        for (int i = 2; i <= N; i++) {
            left = 1;
            right = i;
            target = arr[i][0];
            while (left < right) {
                mid = (left + right) / 2;
                if (target - arr[mid][0] >= S) left = mid + 1;
                else right = mid;
            }
            if (left == i + 1) dp[i][1] = arr[i][1];
            else dp[i][1] = Math.max(dp[left - 1][0], dp[left - 1][1]) + arr[i][1];
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
        }
        System.out.println(Math.max(dp[N][0], dp[N][1]));
    }
}
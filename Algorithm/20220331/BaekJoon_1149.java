package hw_20220331;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1149 {

	static int N, ans;
	static int[][] cost;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_1149_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cost = new int[N][3];
		StringTokenizer st = null;
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 3; c++) {
				cost[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		ans = Integer.MAX_VALUE;
		RGB_dp();
		System.out.println(ans);
	}
	
	private static void RGB_dp() {
		dp = new int[N][3];
		dp[0][0] = cost[0][0];
		dp[0][1] = cost[0][1];
		dp[0][2] = cost[0][2];
		
		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.min(cost[i][0] + dp[i - 1][1], cost[i][0] + dp[i - 1][2]);
			dp[i][1] = Math.min(cost[i][1] + dp[i - 1][0], cost[i][1] + dp[i - 1][2]);
			dp[i][2] = Math.min(cost[i][2] + dp[i - 1][0], cost[i][2] + dp[i - 1][1]);
		}
		
		ans = Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2]));
	}
}
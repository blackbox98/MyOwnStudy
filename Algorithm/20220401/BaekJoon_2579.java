package edu.ssafy.chap09;
/*
6
10
20
15
25
10
20

// 75
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_2579 {

	static int N, ans;
	static int[] stair;
	static int[] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		stair = new int[301];
		for (int i = 1; i <= N; i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}
		
		ans = 0;
		memo = new int[301];
		dp();
		System.out.println(ans);
	}
	private static void dp() {
		memo[1] = stair[1];
		memo[2] = stair[1] + stair[2];
		
		for (int i = 3; i <= N; i++) {
			memo[i] = Math.max(memo[i - 3] + stair[i - 1], memo[i - 2]) + stair[i];
		}
		
		ans = memo[N];
	}
}
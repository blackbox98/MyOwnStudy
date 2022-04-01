package edu.ssafy.chap09;
/////
/*
3
4
7
10

//
7
44
274
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_9095 {

	static int T, N, ans;
	static int[] arr = { 1, 2, 3 };
	static int[] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			ans = 0;
			
			// 중복순열 (시간초과)
//			recursive(0, 0);
			
			// dp
			memo = new int[11];
			dp();
			
			System.out.println(ans);
		}
	}

	private static void dp() {
		// f(n) = f(n - 1) + f(n - 2) + f(n - 3)
		memo[0] = 1;
		memo[1] = 1;
		memo[2] = 2;
		
		for (int i = 3; i <= N; i++) {
			memo[i] = memo[i - 1] + memo[i - 2] + memo[i - 3];
		}
		
		ans = memo[N];
	}

	private static void recursive(int idx, int sum) {
		if (sum > N) {
			return;
		}
		
		if (sum == N) {
			ans++;
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			recursive(idx + 1, sum + arr[i]);
		}
	}
}
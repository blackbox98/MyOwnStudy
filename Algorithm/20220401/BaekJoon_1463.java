package edu.ssafy.chap09;
/*
2	=>	1
10	=>	3
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_1463 {

	static int N;
	static int[] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 재귀 (시간 초과)
//		System.out.println(recursive(N, 0));
		
		// 하향식 dp (실패)
		// 인덱스 숫자에서 1이 되기까지 남은 연산 횟수
//		memo = new int[N + 1];
//		Arrays.fill(memo, -1);
//		System.out.println(recursive_dp(N, 0));
//		System.out.println(Arrays.toString(memo));
		
		// 상향식 dp
		memo = new int[N + 1];
		dp();
	}
	
	private static void dp() {
		// f(n) = f(n - 1) + 1,  f(n) = f(n / 2) + 1,  f(n) = f(n / 3) + 1
		memo[0] = 0;
		memo[1] = 0;
		
		for (int i = 2; i <= N; i++) {
			memo[i] = memo[i - 1] + 1;
			if (i % 2 == 0) {
				memo[i] = Math.min(memo[i], memo[i/2] + 1);
			}
			if (i % 3 == 0) {
				memo[i] = Math.min(memo[i], memo[i/3] + 1);
			}
		}
		System.out.println(memo[N]);
	}
	
	private static int recursive_dp(int n, int cnt) {
		if (n == 1) {
			return cnt;
		}
		
		if(memo[n] != -1) {
			return memo[n];
		}
		
		int r1 = Integer.MAX_VALUE;
		int r2 = Integer.MAX_VALUE;
		int r3 = Integer.MAX_VALUE;
		if(n % 3 == 0) {
			r3 = recursive_dp(n/3, cnt + 1);
		}
		if(n % 2 == 0) {
			r2 = recursive_dp(n/2, cnt + 1);
		}
		r1 = recursive_dp(n - 1, cnt + 1);
		
		return memo[n] = Math.min(r1, Math.min(r2, r3));
	}
	
	private static int recursive(int n, int cnt) {
		if (n == 1) {
			return cnt;
		}
		
		int r1 = Integer.MAX_VALUE;
		int r2 = Integer.MAX_VALUE;
		int r3 = Integer.MAX_VALUE;
		if(n % 3 == 0) {
			r3 = recursive(n/3, cnt + 1);
		}
		if(n % 2 == 0) {
			r2 = recursive(n/2, cnt + 1);
		}
		r1 = recursive(n - 1, cnt + 1);
		return Math.min(r1, Math.min(r2, r3));
	}
}
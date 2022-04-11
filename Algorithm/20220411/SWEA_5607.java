package SWEA;
/*
1
10 2

// #1 45
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5607 {
	
	static long[] memo;
	static final int MOD = 1234567891;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			System.out.printf("#%d %d\n", tc, nCr(N, R));
		}
	}
	
	private static long nCr(int n, int r) {
		if (r == 0 || r == n) {
			return 1L;
		}
		
		memo = new long[n + 1];
		memo[0] = 1L;
		for (int i = 1; i <= n; i++) {
			memo[i] = memo[i - 1] * i % MOD;
		}
		
		return (memo[n] * power(memo[r], MOD - 2, MOD) % MOD *
				power(memo[n - r], MOD - 2, MOD) % MOD) % MOD;
	}

	private static long power(long num1, long num2, long num3) {
		long result = 1L;
		num1 %= num3;
		while (num2 > 0) {
			if (num2 % 2 == 1) {
				result = (result * num1) % num3;
			}
			num2 /= 2;
			num1 = (num1 * num1) % num3;
		}
		return result;
	}
	
	/*
	// 틀린 코드
	private static int nCr(int n, int r) {
		memo = new int[n + 1];
		memo[0] = 0;
		memo[1] = n;
		
		// memo[r] = memo[r - 1] * (n - r + 1) / r;
		for (int i = 2; i <= r; i++) {
			memo[i] = (memo[i - 1] * (n - i + 1)/ i) % MOD;
		}
		return memo[r];
	}
	*/
}
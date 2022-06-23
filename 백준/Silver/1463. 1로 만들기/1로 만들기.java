import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int[] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
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
}
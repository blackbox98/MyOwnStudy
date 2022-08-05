import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int[] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		memo = new int[1001];
		memo[1] = 1;
		memo[2] = 3;
		for (int i = 3; i <= N; i++) {
			memo[i] = (memo[i - 2] * 2 + memo[i - 1]) % 10007;
		}
		System.out.println(memo[N]);
	}
}
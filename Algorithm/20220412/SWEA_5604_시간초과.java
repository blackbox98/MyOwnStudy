package hw_20220412;
/*
 * 시간초과
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5604_시간초과 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_5604_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int ans = 0;
			st = new StringTokenizer(br.readLine());
			long A = Long.parseLong(st.nextToken());
			long B = Long.parseLong(st.nextToken());
			long tmpA = A / 10;
			while(tmpA > 0) {
				ans += tmpA % 10;
				tmpA /= 10;
			}
			ans *= (int) (10 - (A % 10));
			for (int i = (int) (A % 10); i < 10; i++) {
				ans += i;
			}
			A /= 10;
			
			long tmpB = B / 10;
			int sum = 0;
			while(tmpB > 0) {
				sum += tmpB % 10;
				tmpB /= 10;
			}
			ans += sum * ((B % 10) + 1);
			for (int i = 1; i <= (B % 10); i++) {
				ans += i;
			}
			B /= 10;
			
			int res = 0;
			int[] memo = new int[10];
			for (long i = A + 1; i < B; i++) {
				long tmpi = i;
				while(tmpi > 0) {
					memo[(int) (tmpi % 10)]++;
					tmpi /= 10;
				}
			}
			for (int i = 0; i < 10; i++) {
				res += memo[i] * i;
			}
			ans += (res * 10) + (B - (A + 1)) * 45;
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
}
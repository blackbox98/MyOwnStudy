package hw_20220404;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3307 {

	static int T, N, ans;
	static int[] nums, memo;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_3307_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			N = Integer.parseInt(br.readLine());
			nums = new int[N];
			memo = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
				memo[i] = 1;
				for (int j = 0; j < i; j++) {
					if(nums[i] > nums[j] && memo[i] < memo[j] + 1) {
						memo[i] = memo[j] + 1;
					}
				}
				ans = Math.max(ans, memo[i]);
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
}
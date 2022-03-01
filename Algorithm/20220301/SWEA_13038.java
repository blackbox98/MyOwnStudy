package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_13038 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_13038_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int ans = Integer.MAX_VALUE;
			int n = Integer.parseInt(br.readLine());
			String[] str = br.readLine().split(" ");
			for (int i = 0; i < str.length; i++) {
				if (str[i].equals("1")) {
					int d = n;
					int idx = i;
					int cnt = 0;
					while (d > 0) {
						if (str[idx].equals("1")) {
							d--;
						}
						cnt += 1;
						idx = (idx + 1) % (str.length);
					}
					ans = Math.min(ans, cnt);
				}
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
}
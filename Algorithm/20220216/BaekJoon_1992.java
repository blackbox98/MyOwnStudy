package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_1992 {

	static int[][] map;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_1992_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int n = 0; n < N; n++) {
			String str = br.readLine();
			for (int s = 0; s < str.length(); s++) {
				map[n][s] = str.charAt(s) - '0';
			}
		}
		QDTree(N, 0, 0);
		System.out.println(sb);
	}

	private static void QDTree(int N, int r, int c) {
		if (N == 2) {
			int cnt0 = 0;
			int cnt1 = 0;
			sb.append("(");
			for (int i = r; i < r + 2; i++) {
				for (int j = c; j < c + 2; j++) {
					if (map[i][j] == 0) {
						cnt0++;
					}
					if (map[i][j] == 1) {
						cnt1++;
					}
					sb.append(map[i][j]);
				}
			}
			sb.append(")");
			if (cnt0 == 4) {
				sb.delete(sb.length() - 6, sb.length()).append(0);
			}
			if (cnt1 == 4) {
				sb.delete(sb.length() - 6, sb.length()).append(1);
			}
			return;
		}
		sb.append("(");
		QDTree(N / 2, r, c);
		QDTree(N / 2, r, c + N / 2);
		QDTree(N / 2, r + N / 2, c);
		QDTree(N / 2, r + N / 2, c + N / 2);
		sb.append(")");
		check();
	}

	private static void check() {
		int cnt0 = 0;
		int cnt1 = 0;
		for (int i = 0; i < 4; i++) {
			if (sb.charAt(sb.length() - 2 - i) - '0' == 0) {
				cnt0++;
			}
			if (sb.charAt(sb.length() - 2 - i) - '0' == 1) {
				cnt1++;
			}
		}
		if (cnt0 == 4) {
			sb.delete(sb.length()-6, sb.length()).append(0);
		}
		if (cnt1 == 4) {
			sb.delete(sb.length()-6, sb.length()).append(1);
		}
	}
}
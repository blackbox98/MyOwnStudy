package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1074 {

	static int r, c, Ans = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		search((int) Math.pow(2, N), r, c);
		System.out.println(Ans);
	}

	private static void search(int n, int r, int c) {
		if (n == 1) {
			return;
		}

		if (r < n/2 && c < n/2) {
			search(n/2, r, c);
		} else if (r < n/2 && c >= n/2) {
			Ans += (n*n) / 4;
			search(n/2, r, c-n/2);
		} else if (r >= n/2 && c < n/2) {
			Ans += (n*n) / 4 * 2;
			search(n/2, r - n/2, c);
		} else {
			Ans += (n*n) / 4 * 3;
			search(n/2, r - n/2, c - n/2);
		}
	}
}
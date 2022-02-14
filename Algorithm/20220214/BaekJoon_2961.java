package hw_20220214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2961 {

	static int Ans;
	static int[][] taste;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Ans = Integer.MAX_VALUE;
		taste = new int[N][2];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			taste[n][0] = Integer.parseInt(st.nextToken());
			taste[n][1] = Integer.parseInt(st.nextToken());
		}
		cook(new boolean[N], 0, 0);
		System.out.println(Ans);
	}

	private static void cook(boolean[] sel, int idx, int k) {
		if (idx == sel.length) {
			if (k == 0) {
				return;
			}
			int S = 1;
			int D = 0;
			for (int i = 0; i < sel.length; i++) {
				if (sel[i]) {
					S *= taste[i][0];
					D += taste[i][1];
				}
			}
			Ans = Math.min(Ans, Math.abs(S - D));
			return;
		}

		sel[idx] = true;
		cook(sel, idx + 1, k + 1);
		sel[idx] = false;
		cook(sel, idx + 1, k);
	}
}
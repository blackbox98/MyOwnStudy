package SWEA;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class SWEA_9229 {

	static int result;
	static int M;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_9229_input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int N;
		int[] w;
		for (int tc = 1; tc <= T; tc++) {
			result = 0;
			N = sc.nextInt();
			M = sc.nextInt();
			w = new int[N];
			for (int i = 0; i < N; i++) {
				w[i] = sc.nextInt();
			}
			check(w, new int[2], 0, 0);
			if (result == 0) {
				result = -1;
			}
			System.out.printf("#%d %d\n", tc, result);
		}
	}

	public static void check(int[] w, int[] sel, int idx, int k) {
		if (k == sel.length) {
			if ((sel[0] + sel[1]) <= M) {
				result = Math.max(result, (sel[0] + sel[1]));
			}
			return;
		}

		for (int i = idx; i < w.length; i++) {
			sel[k] = w[i];
			check(w, sel, i + 1, k + 1);
		}
	}
}
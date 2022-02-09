package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_15649 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		check(new int[M], new boolean[N], N, 0);
	}

	public static void check(int[] sel, boolean[] ck, int num, int k) {
		if (k == sel.length) {
			for (int i = 0; i < sel.length; i++) {
				System.out.print(sel[i] + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 0; i < num; i++) {
			if (ck[i] == false) {
				ck[i] = true;
				sel[k] = i + 1;
				check(sel, ck, num, k + 1);
				ck[i] = false;
			}
		}
	}
}
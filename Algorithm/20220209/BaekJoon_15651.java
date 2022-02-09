package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_15651 {

	static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		check(new int[M], N, 0);
		System.out.println(str);
	}

	public static void check(int[] sel, int num, int k) {
		if (k == sel.length) {
			for (int i = 0; i < sel.length; i++) {
				str.append(sel[i] + " ");
			}
			str.append("\n");
			return;
		}

		for (int i = 0; i < num; i++) {
			sel[k] = i + 1;
			check(sel, num, k + 1);
		}
	}
}
package hw_20220214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_3040 {

	static int[] num;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = new int[9];
		for (int i = 0; i < 9; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		check(new int[7], 0, 0);
	}

	private static void check(int[] sel, int idx, int k) {
		if (k == sel.length) {
			int sum = 0;
			for (int i = 0; i < sel.length; i++) {
				sum += sel[i];
			}
			if (sum == 100) {
				for (int j = 0; j < sel.length; j++) {
					System.out.println(sel[j]);
				}
			}
			return;
		}
		
		for (int i = idx; i < num.length; i++) {
			sel[k] = num[i];
			check(sel, i + 1, k + 1);
		}
	}
}
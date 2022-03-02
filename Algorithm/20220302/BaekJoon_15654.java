package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_15654 {

	static int[] num;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_15654_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		num = new int[N];
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);
		check(new int[M], new boolean[N], 0);
	}
	private static void check(int[] sel, boolean[] v, int k) {
		if (k == sel.length) {
			for (int i = 0; i < sel.length; i++) {
				System.out.print(sel[i] + " ");
			}
			System.out.println();
			return;
		}
		for (int i = 0; i < v.length; i++) {
			if (!v[i]) {
				v[i] = true;
				sel[k] = num[i];
				check(sel, v, k + 1);
				v[i] = false;
			}
		}
	}
}
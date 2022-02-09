package SWEA;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class SWEA_6958 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_6958_input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int N, M;
		int best, cnt;
		int[] result;
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			result = new int[N];
			best = 0;
			cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					result[i] += sc.nextInt();
				}
				if (result[i] >= best) {
					best = Math.max(best, result[i]);
				}
			}
			for (int i = 0; i < result.length; i++) {
				if (result[i] == best) {
					cnt ++;
				}
			}
			System.out.printf("#%d %d %d\n", tc, cnt, best);
		}
	}
}
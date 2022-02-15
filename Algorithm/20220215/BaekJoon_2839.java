package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon_2839 {

	static int[] pack = { 5, 3 };
	static int Ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		sugarGreedy(N);
		// sugarTotalExp(new int[N/3], N, 0);
		System.out.println(Ans);
	}
	
	// Greedy
	private static void sugarGreedy(int s) {
		int cnt = 0;
		while (s >= 3) {
			if ((s % pack[0]) == 0) {
				cnt += s / pack[0];
				s %= pack[0];
				break;
			} else {
				s -= pack[1];
				cnt++;
			}
		}
		if (s == 0) {
			Ans = cnt;
			return;
		} else {
			cnt = -1;
		}
	}
	
	// 완전 탐색
	private static void sugarTotalExp(int[] sel, int s, int idx) {
		if (s == 0) {
			Ans = Math.min(Ans, idx);
			return;
		}
		if (s < 0) {
			return;
		}
		
		for (int i = 0; i < pack.length; i++) {
			sel[idx] = pack[i];
			sugarTotalExp(sel, s - pack[i], idx + 1);
			sel[idx] = 0;
		}
	}
}
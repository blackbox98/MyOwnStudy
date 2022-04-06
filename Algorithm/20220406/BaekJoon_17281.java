package hw_20220406;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_17281 {

	static int N, ans, result, turn, lastTurn;
	static int[][] lineUp;
	static int[] fourth, total;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_17281_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ans = 0;
		StringTokenizer st = null;
		fourth = new int[N];
		lineUp = new int[N][8];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			fourth[n] = Integer.parseInt(st.nextToken());
			for (int i = 0; i < 8; i++) {
				lineUp[n][i] = Integer.parseInt(st.nextToken());
			}
		}

		permutaion(new boolean[8], new int[8], 0);
		System.out.println(ans);
	}

	private static void permutaion(boolean[] v, int[] sel, int k) {
		if (k == sel.length) {
			// 게임 플레이
			result = 0;
			turn = 0;
			lastTurn = 0;
			for (int n = 0; n < N; n++) {
				total = new int[9];
				int idx = 0;
				total[3] = fourth[n];
				for (int i = 0; i < total.length; i++) {
					if (i == 3)
						continue;
					total[i] = lineUp[n][sel[idx++]];
				}
				playBall(turn);
				turn = lastTurn;
			}
			ans = Math.max(ans, result);
			return;
		}

		for (int i = 0; i < sel.length; i++) {
			if (!v[i]) {
				v[i] = true;
				sel[k] = i;
				permutaion(v, sel, k + 1);
				v[i] = false;
			}
		}
	}

	private static void playBall(int start) {
		int out = 0;
		int player = start;
		int score = 0;
		boolean[] base = new boolean[4];
		while (out < 3) {
			switch (total[player]) {
			case 0:
				out++;
				break;
			case 1:
				for (int i = 3; i > 0; i--) {
					// 이미 출루한 주자가 있는 경우
					if (base[i]) {
						if (i + 1 < 4) {
							base[i] = false;
							base[i + 1] = true;
						} else {
							score++;
							base[i] = false;
						}
					}
				}
				base[1] = true;
				break;
			case 2:
				for (int i = 3; i > 0; i--) {
					// 이미 출루한 주자가 있는 경우
					if (base[i]) {
						if (i + 2 < 4) {
							base[i] = false;
							base[i + 2] = true;
						} else {
							score++;
							base[i] = false;
						}
					}
				}
				base[2] = true;
				break;
			case 3:
				for (int i = 3; i > 0; i--) {
					// 이미 출루한 주자가 있는 경우
					if (base[i]) {
						score++;
						base[i] = false;
					}
				}
				base[3] = true;
				break;
			case 4:
				for (int i = 3; i >= 0; i--) {
					// 이미 출루한 주자가 있는 경우
					if (base[i]) {
						score++;
					}
					base[i] = false;
				}
				score++;
				break;
			}
			player = (player + 1) % 9;
		}
		result += score;
		lastTurn = player;
	}
}
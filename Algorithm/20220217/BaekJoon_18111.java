package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_18111 {

	static int N, M, B, highest, time;
	static int[][] map, tmp;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_18111_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		highest = 0;
		time = Integer.MAX_VALUE;
		map = new int[N][M];
		tmp = new int[N][M];
		int high = Integer.MIN_VALUE;
		int low = Integer.MAX_VALUE;
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				high = Math.max(high, map[r][c]);
				low = Math.min(low, map[r][c]);
			}
		}
		mine(high, low);
		System.out.println(time + " " + highest);
	}

	private static void mine(int high, int low) {
		for (int level = low; level <= high; level++) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					tmp[r][c] = map[r][c];
				}
			}
			int block = B;
			int t = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] < level) {
						int dif = level - map[r][c];
						tmp[r][c] += dif;
						block -= dif;
						t += dif;
					}
					if (map[r][c] > level) {
						int dif = map[r][c] - level;
						tmp[r][c] -= dif;
						block += dif;
						t += dif * 2;
					}
				}
			}
			if (block >= 0 && time >= t) {
				highest = level;
				time = t;
			}
		}
	}

	private static void print(int[][] map) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
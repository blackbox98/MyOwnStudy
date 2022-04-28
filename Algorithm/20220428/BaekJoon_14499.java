package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_14499 {

	static int N, M, x, y;
	static int[][] map;
	static int[] dice;
	/*
	  0
	4 1 5
	  2
	  3
	 */
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_14499_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dice = new int[6];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			play(Integer.parseInt(st.nextToken()));
		}
	}

	private static void play(int turn) {
		switch (turn) {
		case 1:
			if (check(x, y + 1)) {
				int[] tmpDice = new int[6];
				tmpDice[0] = dice[0];
				tmpDice[1] = dice[5];
				tmpDice[2] = dice[2];
				tmpDice[3] = dice[4];
				tmpDice[4] = dice[1];
				tmpDice[5] = dice[3];
				copyDice(tmpDice);
				if (map[x][y + 1] == 0) {
					map[x][y + 1] = dice[3];
				} else {
					dice[3] = map[x][y + 1];
					map[x][y + 1] = 0;
				}
				System.out.println(dice[1]);
				y += 1;
			}
			break;
		case 2:
			if (check(x, y - 1)) {
				int[] tmpDice = new int[6];
				tmpDice[0] = dice[0];
				tmpDice[1] = dice[4];
				tmpDice[2] = dice[2];
				tmpDice[3] = dice[5];
				tmpDice[4] = dice[3];
				tmpDice[5] = dice[1];
				copyDice(tmpDice);
				if (map[x][y - 1] == 0) {
					map[x][y - 1] = dice[3];
				} else {
					dice[3] = map[x][y - 1];
					map[x][y - 1] = 0;
				}
				System.out.println(dice[1]);
				y -= 1;
			}
			break;
		case 3:
			if (check(x - 1, y)) {
				int[] tmpDice = new int[6];
				tmpDice[0] = dice[1];
				tmpDice[1] = dice[2];
				tmpDice[2] = dice[3];
				tmpDice[3] = dice[0];
				tmpDice[4] = dice[4];
				tmpDice[5] = dice[5];
				copyDice(tmpDice);
				if (map[x - 1][y] == 0) {
					map[x - 1][y] = dice[3];
				} else {
					dice[3] = map[x - 1][y];
					map[x - 1][y] = 0;
				}
				System.out.println(dice[1]);
				x -= 1;
			}
			break;
		case 4:
			if (check(x + 1, y)) {
				int[] tmpDice = new int[6];
				tmpDice[0] = dice[3];
				tmpDice[1] = dice[0];
				tmpDice[2] = dice[1];
				tmpDice[3] = dice[2];
				tmpDice[4] = dice[4];
				tmpDice[5] = dice[5];
				copyDice(tmpDice);
				if (map[x + 1][y] == 0) {
					map[x + 1][y] = dice[3];
				} else {
					dice[3] = map[x + 1][y];
					map[x + 1][y] = 0;
				}
				System.out.println(dice[1]);
				x += 1;
			}
			break;
		}
	}

	private static void copyDice(int[] tmpDice) {
		for (int i = 0; i < tmpDice.length; i++) {
			dice[i] = tmpDice[i];
		}
	}

	private static boolean check(int nx, int ny) {
		if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
			return true;
		}
		return false;
	}
}
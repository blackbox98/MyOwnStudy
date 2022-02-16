package SWEA;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class SWEA_7699 {

	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };
	static int T, N, M, Ans;
	static char[][] map;
	static boolean[] alpabat;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_7699_input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			Ans = 0;
			N = sc.nextInt();
			M = sc.nextInt();
			map = new char[N + 1][M + 1];
			alpabat = new boolean[26];
			for (int r = 1; r < map.length; r++) {
				String str = sc.next();
				for (int c = 1; c < map[r].length; c++) {
					map[r][c] = str.charAt(c - 1);
				}
			}
			// print(map);
			alpabat[map[1][1] - 'A'] = true;
			dfs(1, 1, 1);

			System.out.printf("#%d %d\n", tc, Ans);
		}
	}

	private static void dfs(int r, int c, int cnt) {
		Ans = Math.max(Ans, cnt);
		if (cnt == 26)
			return;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr > 0 && nr <= N && nc > 0 && nc <= M && !alpabat[map[nr][nc] - 'A']) {
				alpabat[map[nr][nc] - 'A'] = true;
				dfs(nr, nc, cnt + 1);
				alpabat[map[nr][nc] - 'A'] = false;
			}
		}
	}

	private static void print(char[][] map) {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
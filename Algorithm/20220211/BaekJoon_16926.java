package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_16926 {

	static int T, N, M;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_16926_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][M];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < M; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.println("#" + tc);
			recursive(map, R);
		}
	}

	private static void recursive(int[][] map, int R) {
		// base part
		if (R == 0) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					System.out.print(map[r][c] + " ");
				}
				System.out.println();
			}
			return;
		}
		// inductive part
		int tmp;
		for (int n = 0; n < (Math.min(M, N) / 2); n++) {
			tmp = map[n][n];
			int r = n;
			int c = n;
			int d = 0;
			while (d < 4) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (nr >= n && nr < N - n && nc >= n && nc < M - n) {
					map[r][c] = map[nr][nc];
					r = nr;
					c = nc;
				} else {
					d++;
				}
			}
			map[n + 1][n] = tmp;
		}
		recursive(map, R - 1);
	}
}
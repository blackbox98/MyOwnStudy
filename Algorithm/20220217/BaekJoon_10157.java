package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_10157 {

	static int R, C, K;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] map;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_10157_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		map = new int[R][C];
		if (K <= R * C) {
			check(R - 1, 0, 1);
		} else {
			System.out.println(0);
		}
	}

	private static void check(int r, int c, int cnt) {
		int dir = 0;
		int nr = r;
		int nc = c;
		while (cnt != K) {
			map[r][c] = cnt;
			nr = r + dr[dir];
			nc = c + dc[dir];
			if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] == 0) {
				r = nr;
				c = nc;
				cnt++;
			} else {
				nr -= dr[dir];
				nc -= dc[dir];
				dir = (dir + 1) % 4;
			}
		}
		// print(map);
		System.out.println(r + " " + c);
		System.out.println((c + 1) + " " + (R - r));
	}

	private static void print(int[][] map) {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
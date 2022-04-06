package hw_20220406;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_2580 {

	static final int N = 9;
	static boolean flag = true;
	static int[][] map;
	static ArrayList<Point> list = new ArrayList<Point>();

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_2580_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 0) {
					list.add(new Point(r, c));
				}
			}
		}
		play(map, 0);
	}

	private static void play(int[][] map, int idx) {
		if (idx == list.size()) {
			flag = false;
			print(map);
			return;
		}
		Point p = list.get(idx);
		for (int i = 1; i <= 9; i++) {
			if (flag && check(map, p.r, p.c, i)) {
				map[p.r][p.c] = i;
				play(map, idx + 1);
				map[p.r][p.c] = 0;
			}
		}
	}

	private static boolean check(int[][] tmp, int r, int c, int num) {
		for (int i = 0; i < N; i++) {
			if (tmp[r][i] == num) {
				return false;
			}
			if (tmp[i][c] == num) {
				return false;
			}
		}
		int nr = (r / 3) * 3;
		int nc = (c / 3) * 3;
		for (int i = nr; i < nr + 3; i++) {
			for (int j = nc; j < nc + 3; j++) {
				if (tmp[i][j] == num) {
					return false;
				}
			}
		}
		return true;
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	private static void print(int[][] map) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
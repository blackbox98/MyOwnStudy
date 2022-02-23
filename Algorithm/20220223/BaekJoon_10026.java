package hw_20220223;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BaekJoon_10026 {

	static int N, ans1 = 0, ans2 = 0;
	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };
	static char[][] map;
	static boolean[][] v;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_10026_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < str.length(); c++) {
				map[r][c] = str.charAt(c);
			}
		}
		v = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!v[r][c]) {
					if (section(r, c, 'R')) ans1++;
					if (section(r, c, 'G')) ans1++;
					if (section(r, c, 'B')) ans1++;

				}
			}
		}
		v = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!v[r][c]) {
					if (sections(r, c)) ans2++;
					if (section(r, c, 'B')) ans2++;

				}
			}
		}
		System.out.println(ans1 + " " + ans2);
	}

	private static boolean section(int r, int c, char color) {
		if (map[r][c] != color) {
			return false;
		}
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(r, c));
		v[r][c] = true;

		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (check(nr, nc) && !v[nr][nc] && map[nr][nc] == color) {
					v[nr][nc] = true;
					q.offer(new Point(nr, nc));
				}
			}
		}
		return true;
	}

	private static boolean sections(int r, int c) {
		if (map[r][c] == 'B') {
			return false;
		}
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(r, c));
		v[r][c] = true;

		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (check(nr, nc) && !v[nr][nc] && (map[nr][nc] == 'R' || map[nr][nc] == 'G')) {
					v[nr][nc] = true;
					q.offer(new Point(nr, nc));
				}
			}
		}
		return true;
	}

	private static boolean check(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < N)
			return true;
		return false;
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}
}
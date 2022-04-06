package hw_20220406;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon_17135 {

	static int N, M, D, enemy, ans = 0;
	static int[][] map;
	static ArrayList<Point> death = new ArrayList<Point>();

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_17135_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					enemy++;
				}
			}
		}
		combination(new int[3], new boolean[M], 0, 0);
		System.out.println(ans);
	}

	private static void combination(int[] sel, boolean[] v, int idx, int k) {
		if (k == sel.length) {
			shot(sel, map, 0, enemy);
			return;
		}
		for (int i = idx; i < M; i++) {
			if (!v[i]) {
				v[i] = true;
				sel[k] = i;
				combination(sel, v, i + 1, k + 1);
				v[i] = false;
			}
		}
	}

	private static void shot(int[] sel, int[][] map, int cnt, int total) {
		int[][] tmp = mapCopy(map);
		for (int s = 0; s < sel.length; s++) {
			ArrayList<Point> list = new ArrayList<Point>();
			int er = (N - D < 0 ? 0 : N - D);
			int sc = sel[s] - D + 1;
			int ec = sel[s] + D - 1;
			for (int r = N - 1; r >= er; r--) {
				int d = (N - r - 1);
				for (int c = (sc + d < 0 ? 0 : sc + d); c <= (ec - d >= M ? M - 1 : ec - d); c++) {
					if (map[r][c] == 1) {
						int dist = Math.abs(N - r) + Math.abs(sel[s] - c);
						list.add(new Point(r, c, dist));
					}
				}
			}
			if (list.size() != 0) {
				Collections.sort(list);
				Point p = list.get(0);
				death.add(new Point(p.r, p.c, p.d));
			}
		}
		for (int i = 0; i < death.size(); i++) {
			Point p = death.get(i);
			if (tmp[p.r][p.c] == 1) {
				tmp[p.r][p.c] = 0;
				cnt++;
				total--;
			}
		}
		death.clear();
		for (int r = N; r > 0; r--) {
			for (int c = 0; c < M; c++) {
				tmp[r][c] = tmp[r - 1][c];
				if (tmp[N][c] == 1) {
					tmp[N][c] = 0;
					total--;
				}
				if (r == 1) {
					tmp[0][c] = 0;
				}
			}
		}
		if (total > 0) {
			shot(sel, tmp, cnt, total);
		} else {
			ans = Math.max(ans, cnt);
		}
	}

	private static int[][] mapCopy(int[][] map) {
		int[][] tmpMap = new int[N + 1][M];
		for (int r = 0; r <= N; r++) {
			for (int c = 0; c < M; c++) {
				tmpMap[r][c] = map[r][c];
			}
		}
		return tmpMap;
	}

	static class Point implements Comparable<Point> {
		int r, c, d;

		public Point(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(Point o) {
			if(this.d == o.d) {
				return Integer.compare(this.c, o.c);
			}
			return Integer.compare(this.d, o.d);
		}
	}

	private static void print(int[][] map) {
		for (int r = 0; r <= N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println("======================");
	}
}
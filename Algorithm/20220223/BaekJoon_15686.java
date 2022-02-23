package hw_20220223;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_15686 {

	static int N, M, ans = Integer.MAX_VALUE;
	static int[][] map;
	static ArrayList<Point> ckList;
	static ArrayList<Point> homeList;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_15686_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		ckList = new ArrayList<>();
		homeList = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					homeList.add(new Point(r, c));
				}
				if (map[r][c] == 2) {
					ckList.add(new Point(r, c));
				}
			}
		}
		check(new Point[M], 0, 0);
		System.out.println(ans);
	}

	private static void check(Point[] sel, int idx, int k) {
		if (k == M) {
			int result = 0;
			for (Point hp : homeList) {
				int sum = Integer.MAX_VALUE;
				for (Point cp : sel) {
					int diff = Math.abs(hp.r - cp.r) + Math.abs(hp.c - cp.c);
					sum = Math.min(sum, diff);
				}
				result += sum;
			}
			ans = Math.min(ans, result);
			return;
		}
		for (int i = idx; i < ckList.size(); i++) {
			sel[k] = ckList.get(i);
			check(sel, i + 1, k + 1);
		}
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
package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_14500 {
	static int N, M, ans;
	static int[][] map;
	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };
	static int[][] special = { { 0, 1, 2 }, { 0, 1, 3 }, { 0, 2, 3 }, { 1, 2, 3 } };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_14500_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				ArrayList<Point> v = new ArrayList<Point>();
				v.add(new Point(r, c));
				dfs(r, c, 1, map[r][c], v);

				for (int d = 0; d < 4; d++) {
					int sum = map[r][c];
					boolean flag = true;
					for (int i = 0; i < 3; i++) {
						int nr = r + dr[special[d][i]];
						int nc = c + dc[special[d][i]];
						if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
							sum += map[nr][nc];
						}
						else {
							flag = false;
							break;
						}
					}
					if(flag) {
						ans = Math.max(ans, sum);
					}
				}
			}
		}
		System.out.println(ans);
	}

	private static void dfs(int r, int c, int sel, int sum, ArrayList<Point> v) {
		if (sel == 4) {
			ans = Math.max(ans, sum);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			Point p = new Point(nr, nc);
			if (nr >= 0 && nr < N && nc >= 0 && nc < M && check(nr, nc, v)) {
				v.add(p);
				dfs(nr, nc, sel + 1, sum + map[nr][nc], v);
				v.remove(p);
			}
		}
	}

	private static boolean check(int nr, int nc, ArrayList<Point> v) {
		for (int i = 0; i < v.size(); i++) {
			if (v.get(i).r == nr && v.get(i).c == nc) {
				return false;
			}
		}
		return true;
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
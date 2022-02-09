package SWEA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_1247 {

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
	}

	static int T;
	static int N;
	static int Ans;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_1247_input.txt"));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			Ans = Integer.MAX_VALUE - 1;
			N = sc.nextInt();
			// 회사
			Point company = new Point(sc.nextInt(), sc.nextInt());
			// 집
			Point home = new Point(sc.nextInt(), sc.nextInt());
			// 고객 위치
			Point[] cus = new Point[N];
			for (int i = 0; i < cus.length; i++) {
				cus[i] = new Point(sc.nextInt(), sc.nextInt());
			}

			// 순열
			permutaion(cus, new Point[N], new boolean[N], 0, company, home);
			System.out.printf("#%d %d\n", tc, Ans);
		}
	}

	private static void permutaion(Point[] cus, Point[] sel, boolean[] v, int idx, Point company, Point home) {
		// base part
		if (idx == N) {
			int dist = 0;
			// 회사에서 첫번째 고객까지의 거리
			dist = Math.abs(company.x - sel[0].x) + Math.abs(company.y - sel[0].y);
			// 고객들 간의 거리
			for (int i = 0; i < N - 1; i++) {
				dist += Math.abs(sel[i].x - sel[i + 1].x) + Math.abs(sel[i].y - sel[i + 1].y);
			}
			// 마지막 고객에서 집까지의 거리
			dist += Math.abs(sel[N - 1].x - home.x) + Math.abs(sel[N - 1].y - home.y);
			Ans = Math.min(Ans, dist);
			return;
		}

		// inductive part
		for (int i = 0; i < N; i++) {
			if (!v[i]) {
				v[i] = true;
				sel[idx] = cus[i];
				permutaion(cus, sel, v, idx + 1, company, home);
				v[i] = false;
			}
		}
	}
}
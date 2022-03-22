package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekJoon_2146 {

	static int N, ans;
	static int[][] map, tmp;
	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };
	static ArrayList<Point>[] points;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_2146_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ans = Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		tmp = new int[N][N];
		StringTokenizer st = null;
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		points = new ArrayList[N*N / 2 + 1];
		boolean[][] v = new boolean[N][N];
		int idx = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(map[r][c] == 1 && !v[r][c]) {
					points[idx] = new ArrayList<>();
					Queue<Point> queue = new LinkedList<>();
					queue.offer(new Point(r, c));
					tmp[r][c] = idx + 1;
					v[r][c] = true;
					while (!queue.isEmpty()) {
						Point p = queue.poll();
						boolean flag = true;
						for (int d = 0; d < 4; d++) {
							int nr = p.r + dr[d];
							int nc = p.c + dc[d];
							if(nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc]) {
								if (map[nr][nc] == 1) {
									v[nr][nc] = true;
									queue.offer(new Point(nr, nc));
									tmp[nr][nc] = idx + 1;
								} else if (map[nr][nc] == 0 && flag) {
									flag = false;
									points[idx].add(new Point(p.r, p.c));
								}
							}
						}
					}
					idx++;
				}
			}
		}
		for (int n = 0; n < idx; n++) {
			for (int i = 0; i < points[n].size(); i++) {
				bridge(points[n].get(i), n + 1);
			}
		}
		if (ans == Integer.MAX_VALUE) {
			ans = 0;
		}
		System.out.println(ans);
	}

	private static void bridge(Point start, int n) {
		boolean[][] v = new boolean[N][N];
		Queue<EdgePoint> queue = new LinkedList<>();
		queue.offer(new EdgePoint(start.r, start.c, 0));
		v[start.r][start.c] = true;
		while (!queue.isEmpty()) {
			EdgePoint ep = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = ep.r + dr[d];
				int nc = ep.c + dc[d];
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc] && tmp[nr][nc] != n) {
					v[nr][nc] = true;
					queue.offer(new EdgePoint(nr, nc, ep.cnt + 1));
					if (tmp[nr][nc] != 0) {
						ans = Math.min(ans, ep.cnt);
					}
				}
			}
		}
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
	}
	
	static class EdgePoint {
		int r, c, cnt;

		public EdgePoint(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
}
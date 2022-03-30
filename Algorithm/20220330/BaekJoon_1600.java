package BaekJoon;
/*
1
4 4
0 0 0 0
1 0 0 0
0 0 1 0
0 1 0 0
// 4

2
5 2
0 0 1 1 0
0 0 1 1 0
// -1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_1600 {

	static int K, W, H, ans;
	static int[][] map;
	static boolean[][][] v;
	static int[] dr = { 0, 1, 0, -1, -2, -1, 1, 2, 2, 1, -1, -2 }; // 우 하 좌 상 우상좌 우상우 우하좌 우하우 좌하좌 좌하우 좌상좌 좌상우
	static int[] dc = { 1, 0, -1, 0, 1, 2, 2, 1, -1, -2, -2, -1 };
//	static int[] hdr = { -2, -1, 1, 2, 2, 1, -1, -2 }; // 우상좌 우상우 우하좌 우하우 좌하좌 좌하우 좌상좌 좌상우
//	static int[] hdc = { 1, 2, 2, 1, -1, -2, -2, -1 };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		for (int r = 0; r < H; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < W; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		ans = Integer.MAX_VALUE;
		bfs(0, 0);
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	private static void bfs(int r, int c) {
		Queue<Point> queue = new LinkedList<Point>();
		v = new boolean[H][W][K+1];
		queue.offer(new Point(r, c, 0, K));
		v[r][c][K] = true;
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			if (p.r == H - 1 && p.c == W - 1) {
				ans = Math.min(ans, p.cnt);
				return;
			}
			for (int d = 0; d < (p.hm > 0 ? 12 : 4); d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				int nh = d < 4 ? p.hm : p.hm - 1;
				if (nr >= 0 && nr < H && nc >= 0 && nc < W && !v[nr][nc][nh] && map[nr][nc] == 0) {
					v[nr][nc][nh] = true;
					queue.offer(new Point(nr, nc, p.cnt+1, nh));
				}
			}
		}
	}
	
	static class Point {
		int r, c, cnt, hm;

		public Point(int r, int c, int dist, int hm) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = dist;
			this.hm = hm;
		}
	}
}
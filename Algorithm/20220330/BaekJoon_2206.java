package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_2206 {

	static int N, M, ans;
	static int[][] map;
	static boolean[][][] v;
	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };
	static ArrayList<Point> points;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_2206_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		v = new boolean[N][M][2];
		for (int r = 0; r < N; r++) {
			String s = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = s.charAt(c) - '0';
				if (map[r][c] == 1) {
				}
			}
		}
		ans = Integer.MAX_VALUE;
		bfs(v, 0, 0);
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	private static void bfs(boolean[][][] v, int r, int c) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(r, c, 1, 0));
		v = new boolean[N][M][2];
		v[r][c][0] = true;
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			if (p.r == N - 1 && p.c == M - 1) {
				ans = Math.min(ans, p.dist);
				return;
			}
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
					if (map[nr][nc] == 0 && !v[nr][nc][p.crush]) {
						v[nr][nc][p.crush] = true;
						queue.offer(new Point(nr, nc, p.dist+1, p.crush));
					} else if (map[nr][nc] == 1 && p.crush == 0) {
						v[nr][nc][1] = true;
						queue.offer(new Point(nr, nc, p.dist+1, 1));
					}
				}
			}
		}
	}
	
	static class Point {
		int r, c, dist, crush;

		public Point(int r, int c, int dist, int crush) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.crush = crush;
		}
	}
}
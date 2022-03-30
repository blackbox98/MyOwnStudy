package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_1194 {

	static int N, M, ans;
	static char[][] map;
	static boolean[][][] v;
	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_1194_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int startR = 0;
		int startC = 0;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int r = 0; r < N; r++) {
			String s = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = s.charAt(c);
				if (map[r][c] - '0' == 0) {
					startR = r;
					startC = c;
				}
			}
		}
		ans = Integer.MAX_VALUE;
		bfs(startR, startC);
		print(map);
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	private static void print(char[][] map) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

	private static void bfs(int r, int c) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(r, c, 0, 0));
		v = new boolean[N][M][1<<7];
		v[r][c][0] = true;
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			int num = 0;
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !v[nr][nc][p.keys] && map[nr][nc] != '#') {
					v[nr][nc][p.keys] = true;
					if (map[nr][nc] == '1') {
						ans = Math.min(ans, p.dist + 1);
						return;
					} else if (map[nr][nc] - 'a' >= 0 && map[nr][nc] - 'a' < 6) {
						num = p.keys | (1 << (map[nr][nc] - 'a'));
					} else if (map[nr][nc] - 'A' >= 0 && map[nr][nc] - 'A' < 6) {
						if ((p.keys & (1 << (map[nr][nc] - 'A'))) == 0) {
							continue;
						} else {
							num = p.keys;
						}
					} else {
						num = p.keys;
					}
					queue.offer(new Point(nr, nc, p.dist + 1, num));
				}
			}
		}
	}

	static class Point {
		int r, c, dist, keys;

		public Point(int r, int c, int dist, int keys) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.keys = keys;
		}
	}
}
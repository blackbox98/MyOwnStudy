package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
13 12
0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 1 1 0 0 0
0 1 1 1 0 0 0 1 1 0 0 0
0 1 1 1 1 1 1 0 0 0 0 0
0 1 1 1 1 1 0 1 1 0 0 0
0 1 1 1 1 0 0 1 1 0 0 0
0 0 1 1 0 0 0 1 1 0 0 0
0 0 1 1 1 1 1 1 1 0 0 0
0 0 1 1 1 1 1 1 1 0 0 0
0 0 1 1 1 1 1 1 1 0 0 0
0 0 1 1 1 1 1 1 1 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0

//
3
5
 */

// 0기준 4방탐색
public class BaekJoon_2636 {

	static int R, C, cheese, hour, ans;
	static int[][] map;
	static boolean[][] v;
	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		cheese = 0;
		hour = 0;
		ans = 0;
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					cheese++;
				}
			}
		}
		
		while(cheese > 0) {
			ans = cheese;
			dfs(mapCopy(map), new boolean[R][C], 0, 0);
//			bfs(0, 0);
			hour++;
		}
		
		System.out.println(hour);
		System.out.println(ans);
	}
	
	private static void dfs(int[][] tmp, boolean[][] v, int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr >= 0 && nr < R && nc >= 0 && nc < C && !v[nr][nc]) {
				v[nr][nc] = true;
				if (tmp[nr][nc] == 0) {
					dfs(tmp, v, nr, nc);
				} else {
					map[nr][nc] = 0;
					cheese--;
					if(cheese == 0) {
						return;
					}
				}
			}
		}
	}
	
	private static void bfs(int r, int c) {
		int[][] tmp = mapCopy(map);
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(r, c));
		v = new boolean[R][C];
		v[r][c] = true;
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (nr >= 0 && nr < R && nc >= 0 && nc < C && !v[nr][nc]) {
					v[nr][nc] = true;
					if (tmp[nr][nc] == 0) {
						queue.offer(new Point(nr, nc));
					} else {
						map[nr][nc] = 0;
						cheese--;
						if(cheese == 0) {
							return;
						}
					}
				}
			}
		}
	}
	
	private static int[][] mapCopy(int[][] map2) {
		int[][] tmpMap = new int[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				tmpMap[r][c] = map[r][c];
			}
		}
		return tmpMap;
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
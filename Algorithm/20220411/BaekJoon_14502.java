package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_14502 {

	static int N, M, ans = Integer.MIN_VALUE;
	static int[][] map;
	static ArrayList<Point> safeArea = new ArrayList<Point>();
	static ArrayList<Point> virus = new ArrayList<Point>();
	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_14502_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 0) {
					safeArea.add(new Point(r, c));
				} else if (map[r][c] == 2) {
					virus.add(new Point(r, c));
				}
			}
		}
		addWall(new Point[3], 0, 0);
		System.out.println(ans);
	}

	private static void addWall(Point[] sel, int idx, int k) {
		if (k == sel.length) {
			int[][] tmp = mapCopy(map);
			tmp[sel[0].r][sel[0].c] = 1;
			tmp[sel[1].r][sel[1].c] = 1;
			tmp[sel[2].r][sel[2].c] = 1;
			bfs(tmp);
			return;
		}

		for (int i = idx; i < safeArea.size(); i++) {
			sel[k] = safeArea.get(i);
			addWall(sel, i + 1, k + 1);
		}
	}

	private static void bfs(int[][] tmp) {
		int cnt = safeArea.size() - 3;
		Queue<Point> queue = new LinkedList<Point>();
		boolean[][] v = new boolean[N][M];
		for (int i = 0; i < virus.size(); i++) {
			queue.offer(virus.get(i));
			while (!queue.isEmpty()) {
				Point p = queue.poll();
				v[p.r][p.c] = true;
				for (int d = 0; d < 4; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];
					if (nr >= 0 && nr < N && nc >= 0 && nc < M && !v[nr][nc] && tmp[nr][nc] == 0) {
						queue.offer(new Point(nr, nc));
						tmp[nr][nc] = 2;
						cnt--;
					}
				}
			}
		}
//		print(tmp);
		ans = Math.max(ans, cnt);
	}

	private static int[][] mapCopy(int[][] map) {
		int[][] tmpMap = new int[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				tmpMap[r][c] = map[r][c];
			}
		}
		return tmpMap;
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	private static void print(int[][] map) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println("================================");
	}
}
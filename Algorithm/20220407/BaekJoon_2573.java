package hw_20220407;

/*
5 7
0 0 0 0 0 0 0
0 2 4 5 3 0 0
0 3 0 2 5 2 0
0 7 6 2 4 0 0
0 0 0 0 0 0 0

// 2
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_2573 {

	static int N, M, ans;
	static int[][] map;
	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };
	static ArrayList<Point> list = new ArrayList<>();
	static boolean[][] v;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] != 0) {
					list.add(new Point(r, c));
				}
			}
		}
		ans = 0;
		flag = true;
		while (flag) {
			ans++;
			melt();
		}
		
		System.out.println(ans);
	}

	private static void melt() {
		int[][] tmp = mapCopy(map);
		for (int i = 0; i < list.size(); i++) {
			Point p = list.get(i);
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && tmp[nr][nc] == 0) {
					map[p.r][p.c]--;
					if (map[p.r][p.c] == 0) {
						list.remove(i--);
						break;
					}
				}
			}
		}
		if (!list.isEmpty() && !check(list.get(0))) {
			flag = false;
		}
		if (list.isEmpty()) {
			ans = 0;
			flag = false;
		}
	}

	private static boolean check(Point start) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(start.r, start.c));
		v = new boolean[N][M];
		v[start.r][start.c] = true;
		int cnt = 0;
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			cnt++;
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !v[nr][nc] && map[nr][nc] != 0) {
					v[nr][nc] = true;
					queue.offer(new Point(nr, nc));
				}
			}
		}
		
		if (cnt == list.size()) {
			return true;
		}
		return false;
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
			super();
			this.r = r;
			this.c = c;
		}
	}
}
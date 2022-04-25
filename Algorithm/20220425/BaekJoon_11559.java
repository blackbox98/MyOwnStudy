package BaekJoon;
/*
......
......
......
......
......
......
......
......
.Y....
.YG...
RRYG..
RRYGG.

// 3
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BaekJoon_11559 {

	static int R, C, ans;
	static char[][] map;
	static ArrayList<Point> list, crush;
	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		R = 12;
		C = 6;
		ans = 0;
		map = new char[R][C];
		list = new ArrayList<Point>();
		for (int r = 0; r < R; r++) {
			char[] s = br.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				map[r][c] = s[c];
				if (map[r][c] != '.') {
					list.add(new Point(r, c, map[r][c]));
				}
			}
		}
		while(true) {
			crush = new ArrayList<Point>();
			boolean[][] v = new boolean[R][C];
			int n = list.size();
			for (int i = 0; i < n; i++) {
				if (!v[list.get(i).r][list.get(i).c]) {
					play(v, i, 1);
				}
			}
			for (int i = 0; i < crush.size(); i++) {
				for (int j = 0; j < list.size(); j++) {
					if (crush.get(i).r == list.get(j).r && crush.get(i).c == list.get(j).c) {
						map[crush.get(i).r][crush.get(i).c] = '.';
						list.remove(j);
					}
				}
			}
			if (n == list.size()) {
				break;
			}
			ans++;
			if (list.isEmpty()) {
				break;
			}
			list.clear();
			for (int c = 0; c < C; c++) {
				ArrayList<Character> tmpList = new ArrayList<Character>();
				for (int r = R - 1; r >= 0; r--) {
					if (map[r][c] != '.') {
						tmpList.add(map[r][c]);
						map[r][c] = '.';
					}
				}
				for (int i = 0; i < tmpList.size(); i++) {
					map[R - 1 - i][c] = tmpList.get(i);
					list.add(new Point(R - 1 - i, c, map[R - 1 - i][c]));
				}
			}
//			print(map);
		}
		System.out.println(ans);
	}

	private static void play(boolean[][] v, int idx, int cnt) {
		ArrayList<Point> tmpCrush = new ArrayList<Point>();
		v[list.get(idx).r][list.get(idx).c] = true;
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(list.get(idx).r, list.get(idx).c, list.get(idx).color));
		tmpCrush.add(new Point(list.get(idx).r, list.get(idx).c, list.get(idx).color));
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (nr >= 0 && nr < R && nc >= 0 && nc < C && !v[nr][nc] && p.color == map[nr][nc]) {
					v[nr][nc] = true;
					queue.offer(new Point(nr, nc, map[nr][nc]));
					tmpCrush.add(new Point(nr, nc, map[nr][nc]));
				}
			}
		}
		if (tmpCrush.size() >= 4) {
			for (int i = 0; i < tmpCrush.size(); i++) {
				crush.add(tmpCrush.get(i));
			}
		}
	}
	
	private static void print(char[][] map) {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static class Point {
		int r, c;
		char color;
		
		public Point(int r, int c, char color) {
			this.r = r;
			this.c = c;
			this.color = color;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", color=" + color + "]";
		}
	}
}
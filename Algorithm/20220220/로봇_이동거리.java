package Daily_Practice;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 로봇_이동거리 {

	static int N, ans;
	static int[] dr = { 0, 1, 0, -1}; // 우, 하, 좌, 상
	static int[] dc = { 1, 0, -1, 0 };
	static int[][] dir = { { 0 }, { 0, 2 }, { 0, 1, 2, 3 } };
	static ArrayList<Point> list;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/로봇_이동거리.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			N = Integer.parseInt(br.readLine());
			char[][] map = new char[N][N];
			list = new ArrayList<>();
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = st.nextToken().charAt(0);
					if (map[r][c] != 'W' && map[r][c] != 'S') {
						list.add(new Point(r, c, map[r][c]));
					}
				}
			}
			move(map, 0);
			//print(map);
			System.out.println(ans);
		}
	}

	private static void move(char[][] map, int idx) {
		for (int i = 0; i < list.size(); i++) {
			Point p = list.get(i);
			for (int d = 0; d < dir[p.robot - 'A'].length; d++) {
				int r = p.r;
				int c = p.c;
				while (true) {
					int nr = r + dr[dir[map[p.r][p.c] - 'A'][d]];
					int nc = c + dc[dir[map[p.r][p.c] - 'A'][d]];
					if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 'S') {
						ans++;
						r = nr;
						c = nc;
					} else {
						break;
					}
				}
			}
		}
	}

	static class Point {
		int r, c, robot;

		Point(int r, int c, char robot) {
			this.r = r;
			this.c = c;
			this.robot = robot;
		}
	}

	private static void print(char[][] map) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
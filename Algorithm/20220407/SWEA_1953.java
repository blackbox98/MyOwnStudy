package hw_20220407;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953 {

	static int N, M, R, C, L, ans;
	static int[][] map;
	/*
	  0
	3 x 1
	  2
	 */
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] dir = { {}, {0, 1, 2, 3}, {0, 2}, {1, 3}, {0, 1}, {1, 2}, {2, 3}, {0, 3} };
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_1953_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < M; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			ans = 0;
			bfs(R, C);
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
	
	private static void bfs(int sr, int sc) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(sr, sc, map[sr][sc], 1));
		boolean[][] v = new boolean[N][M];
		v[sr][sc] = true;
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			ans++;
			if (p.depth == L) {
				continue;
			}
			for (int d = 0; d < dir[p.type].length; d++) {
				int nr = p.r + dr[dir[p.type][d]];
				int nc = p.c + dc[dir[p.type][d]];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !v[nr][nc] && map[nr][nc] != 0 && check((dir[p.type][d] + 2) % 4, nr, nc)) {
					v[nr][nc] = true;
					queue.offer(new Point(nr, nc, map[nr][nc], p.depth + 1));
				}
			}
		}
	}

	private static boolean check(int nDir, int nr, int nc) {
		for (int i = 0; i < dir[map[nr][nc]].length; i++) {
			if (nDir == dir[map[nr][nc]][i]) {
				return true;
			}
		}
		return false;
	}

	static class Point {
		int r, c, type, depth;

		public Point(int r, int c, int type, int depth) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
			this.depth = depth;
		}
	}

	private static void print(int[][] map) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println("==================");
	}
}
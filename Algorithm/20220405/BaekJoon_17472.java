package hw_20220405;
/*
미완성본
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_17472 {

	static int N, M, ans;
	static int[][] res;
	static int[][] map, tmp;
	static boolean[][] v, check;
	static ArrayList<Point>[] list;
	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_17472_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		list = new ArrayList[6];
		int idx = 0;
		tmp = new int[N][M];
		v = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (!v[r][c] && map[r][c] == 1) {
					list[idx] = new ArrayList<Point>();
					v[r][c] = true;
					Queue<Point> queue = new LinkedList<Point>();
					queue.offer(new Point(r, c));
					tmp[r][c] = idx + 1;
					while (!queue.isEmpty()) {
						boolean flag = true;
						Point p = queue.poll();
						for (int d = 0; d < 4; d++) {
							int nr = p.r + dr[d];
							int nc = p.c + dc[d];
							if (nr >= 0 && nr < N && nc >= 0 && nc < M && !v[nr][nc]) {
								if (map[nr][nc] == 1) {
									v[nr][nc] = true;
									queue.offer(new Point(nr, nc));
									tmp[nr][nc] = idx + 1;
								} else if (map[nr][nc] == 0 && flag) {
									flag = false;
									list[idx].add(new Point(p.r, p.c));
								}
							}
						}
					}
					idx++;
				}
			}
		}
		res = new int[idx][idx];
		check = new boolean[idx][idx];
		for (int n = 0; n < idx; n++) {
			Arrays.fill(res[n], Integer.MAX_VALUE);
			for (int i = 0; i < list[n].size(); i++) {
				bridge(list[n].get(i), n + 1);
			}
		}
		print(tmp);
		for (int i = 0; i < idx; i++) {
			for (int j = 0; j < idx; j++) {
				if(check[i][j]) {
					res[i][j] = (res[i][j] == Integer.MAX_VALUE ? 0 : res[i][j]);
					ans += res[i][j];
					if (check[j][i]) {
						check[j][i] = false;
					}
				}
			}
		}
		System.out.println(ans == 0 ? -1 : ans);
	}

	private static void print(int[][] map) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

	private static void bridge(Point p, int idx) {
		v = new boolean[N][M];
		for (int d = 0; d < 4; d++) {
			int nr = p.r;
			int nc = p.c;
			int nd = 0;
			while (true) {
				nr += dr[d];
				nc += dc[d];
				nd++;
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !v[nr][nc] && tmp[nr][nc] != idx) {
					v[nr][nc] = true;
					if(tmp[nr][nc] != 0) {
						if (nd >= 3) {
							System.out.printf("tmp[%d][%d] = %d\n", nr, nc, tmp[nr][nc]);
							res[idx - 1][tmp[nr][nc] - 1] = Math.min(res[idx - 1][tmp[nr][nc] - 1], nd - 1);
							check[idx - 1][tmp[nr][nc] - 1] = true;
							break;
						} else {
							break;
						}
					}
				} else {
					break;
				}
			}
		}
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
	}
}
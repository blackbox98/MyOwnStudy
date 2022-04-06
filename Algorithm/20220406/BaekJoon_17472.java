package hw_20220406;

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
	static boolean[][] v;
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
		for (int n = 0; n < idx; n++) {
			Arrays.fill(res[n], Integer.MAX_VALUE);
			for (int i = 0; i < list[n].size(); i++) {
				bridge(list[n].get(i), n + 1);
			}
		}
		for (int r = 0; r < idx; r++) {
			for (int c = 0; c < idx; c++) {
				if(res[r][c] == Integer.MAX_VALUE) {
					res[r][c] = 0;
				}
			}
		}
		// prim
		int V = idx;
		int[] dist = new int[V];
		boolean[] v = new boolean[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		for (int cnt = 0; cnt < V - 1; cnt++) {
			int minD = Integer.MAX_VALUE;
			int minIdx = -1;
			for (int i = 0; i < V; i++) {
				if(!v[i] && dist[i] < minD) {
					minD = dist[i];
					minIdx = i;
				}
			}
			if (minIdx == -1) {
				break;
			}
			v[minIdx] = true;
			for (int i = 0; i < V; i++) {
				if(res[minIdx][i] != 0 && !v[i] && dist[i] > res[minIdx][i]) {
					dist[i] = res[minIdx][i];
				}
			}
		}
		
		for (int i = 0; i < V; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				ans = -1;
				break;
			}
			ans += dist[i];
		}
		System.out.println(ans);
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
							res[idx - 1][tmp[nr][nc] - 1] = Math.min(res[idx - 1][tmp[nr][nc] - 1], nd - 1);
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
	}

	private static void print(int[][] map) {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
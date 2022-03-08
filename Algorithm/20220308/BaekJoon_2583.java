package BaekJoon;
/*
5 7 3
0 2 4 4
1 1 2 5
4 0 6 2

//
3
1 7 13
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class BaekJoon_2583 {

	static int M, N, K, cnt;
	static int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		K = Integer.parseInt(st.nextToken());
		Rect[] rects = new Rect[K];
		for (int i = 0; i < K; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < 4; j++) {
				rects[i] = new Rect(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]), Integer.parseInt(str[3]));
			}
			if (rects[i].sr < rects[i].er) {
				if (rects[i].sc < rects[i].ec) {
					for (int k = rects[i].sr; k < rects[i].er; k++) {
						for (int n = rects[i].sc; n < rects[i].ec; n++) {
							map[k][n] = true;
						}
					}
				} else {
					for (int k = rects[i].sr; k < rects[i].er; k++) {
						for (int n = rects[i].ec; n < rects[i].sc; n++) {
							map[k][n] = true;
						}
					}
				}
			} else {
				if (rects[i].sc > rects[i].ec) {
					for (int k = rects[i].er; k < rects[i].sr; k++) {
						for (int n = rects[i].ec; n < rects[i].sc; n++) {
							map[k][n] = true;
						}
					}
				} else {
					for (int k = rects[i].er; k < rects[i].sr; k++) {
						for (int n = rects[i].sc; n < rects[i].ec; n++) {
							map[k][n] = true;
						}
					}
				}
			}
		}
		ArrayList<Integer> result = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (!map[r][c]) {
					cnt = 0;
					check(r, c);
					result.add(cnt);
				}
			}
		}
		Collections.sort(result);
		System.out.println(result.size());
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}
	}
	
	private static void check(int r, int c) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(r, c));
		map[r][c] = true;
		cnt++;
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !map[nr][nc]) {
					map[nr][nc] = true;
					cnt++;
					queue.offer(new Point(nr, nc));
				}
			}
		}
	}

	static class Rect {
		int sr, sc, er, ec;

		public Rect(int sr, int sc, int er, int ec) {
			super();
			this.sr = sr;
			this.sc = sc;
			this.er = er;
			this.ec = ec;
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
}
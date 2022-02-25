package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_17144 {

	static int R, C, T;
	static int[][] map;
	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_17144_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		int[] cm = new int[2];
		int idx = 0;
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == -1) {
					cm[idx++] = r;
				}
			}
		}
		cleaning(cm[0], cm[1]);
		int sum = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				sum += map[r][c];
			}
		}
		System.out.println(sum+2);
	}

	private static void cleaning(int start, int end) {
		while (T > 0) {
			// 맵 복사
			int[][] tmp = new int[R][C];
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					tmp[i][j] = map[i][j];
				}
			}
			// 확산
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					// 공기청정기에서는 탐색이 일어나지 않음
					if ((r == start && c == 0) || (r == end && c == 0)) {
						continue;
					}
					// 4방 탐색
					// 빈공간인 경우
					if (tmp[r][c] == 0) {
						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							if (nr >= 0 && nr < R && nc >= 0 && nc < C && tmp[nr][nc] > 0) {
								map[r][c] += (tmp[nr][nc] / 5);
							}
						}
					} else {
						int cnt = 0;
						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							if (nr >= 0 && nr < R && nc >= 0 && nc < C && tmp[nr][nc] != -1) {
								map[r][c] -= (tmp[r][c] / 5);
								if (tmp[nr][nc] != 0) {
									map[r][c] += (tmp[nr][nc] / 5);
								}
							}
						}
					}
				}
			}
			// 공기청정기 작동
			int r = start - 1;
			int c = 0;
			int dir = 3;
			// 상단
			while (true) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				if (nr >= 0 && nr < end && nc >= 0 && nc < C) {
					if (map[nr][nc] == -1) {
						map[r][c] = 0;
						break;
					}
					map[r][c] = map[nr][nc];
					r = nr;
					c = nc;
				} else {
					dir = (dir + 1) % 4;
				}
			}
			// 하단
			r = end + 1;
			c = 0;
			dir = 1;
			while (true) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				if (nr > start && nr < R && nc >= 0 && nc < C) {
					if (map[nr][nc] == -1) {
						map[r][c] = 0;
						break;
					}
					map[r][c] = map[nr][nc];
					r = nr;
					c = nc;
				} else {
					dir -= 1;
					if (dir < 0) {
						dir = 3;
					}
				}
			}
			T--;
		}
	}
}
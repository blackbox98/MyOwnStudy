package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_5212 {

	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { -1, 0, 1, 0 };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] sea = new char[R + 2][C + 2];
		for (int r = 0; r < sea.length; r++) {
			Arrays.fill(sea[r], '.');
		}
		for (int r = 1; r < sea.length - 1; r++) {
			String str = br.readLine();
			for (int c = 1; c < sea[r].length - 1; c++) {
				sea[r][c] = str.charAt(c - 1);
			}
		}
		int sr = R;
		int sc = C;
		int er = 0;
		int ec = 0;
		char[][] map = new char[R + 2][C + 2];
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = sea[r][c]; 
			}
		}
		for (int r = 1; r < sea.length - 1; r++) {
			for (int c = 1; c < sea[r].length - 1; c++) {
				if (sea[r][c] == 'X') {
					int cnt = 0;
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (sea[nr][nc] == '.') {
							cnt++;
						}
					}
					if (cnt >= 3) {
						map[r][c] = '.';
					} 
					else {
						sr = Math.min(sr, r);
						sc = Math.min(sc, c);
						er = Math.max(er, r);
						ec = Math.max(ec, c);
					}
				}
			}
		}
		for (int r = sr; r <= er; r++) {
			for (int c = sc; c <= ec; c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}
	}
}
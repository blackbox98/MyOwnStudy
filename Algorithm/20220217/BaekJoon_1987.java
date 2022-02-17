package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1987 {

	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };
	static char[][] map;
	static int[] alpha = new int[26];
	static int R, C, ans = 1;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_1987_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
			}
		}
		alpha[map[0][0] - 'A']++;
		check(0, 0, 1);
		System.out.println(ans);
	}
	
	private static void check(int r, int c, int cnt) {
		ans = Math.max(ans, cnt);
		for (int d = 0; d < dr.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr >= 0 && nr < R && nc >= 0 && nc < C && alpha[map[nr][nc] - 'A'] == 0) {
				alpha[map[nr][nc] - 'A']++;
				check(nr, nc, cnt + 1);
				alpha[map[nr][nc] - 'A']--;
			}
		}
	}
}
package hw_20220224;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_11315 {
	static int[] dr = { 0, 1, 1, 1 }; // 우, 우하, 하, 하좌
	static int[] dc = { 1, 1, 0, -1 }; 
	static int N;
	static boolean ans;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_11315_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = false;
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			for (int r = 0; r < N; r++) {
				String str = br.readLine();
				for (int c = 0; c < N; c++) {
					map[r][c] = str.charAt(c);
				}
			}
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == 'o') {
						check(r, c, 1);
					}
				}
			}
			if (ans) {
				System.out.println("#" + tc + " " + "YES");
			} else {
				System.out.println("#" + tc + " " + "NO");
			}
		}
	}
	private static void check(int r, int c, int cnt) {
		int d = 0;
		int or = r;
		int oc = c;
		while (d < 4) {
			int nr = or + dr[d];
			int nc = oc + dc[d];
			if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 'o') {
				or = nr;
				oc = nc;
				cnt++;
			} else {
				d++;
				or = r;
				oc = c;
				cnt = 1;
			}
			if (cnt == 5) {
				ans = true;
				return;
			}
		}
	}
}
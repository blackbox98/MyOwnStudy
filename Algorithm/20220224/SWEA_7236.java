package hw_20220224;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7236 {
	// 0 1 2
	// 7 x 3
	// 6 5 4
	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };
	static int N, ans;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_7236_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			StringTokenizer st = null;
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = st.nextToken().charAt(0);
				}
			}
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == 'W') {
						check(r, c, 0);
					}
				}
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
	private static void check(int r, int c, int cnt) {
		for (int d = 0; d < dc.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 'W') {
				cnt++;
			}
		}
		if (cnt == 0) cnt++;
		ans = Math.max(ans, cnt);
	}
}
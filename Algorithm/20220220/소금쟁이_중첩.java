package Daily_Practice;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 소금쟁이_중첩 {

	static int ans;
	static int[] dr = { 1, 0 };
	static int[] dc = { 0, 1 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/소금쟁이_중첩.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			int unit = Integer.parseInt(st.nextToken());
			L: for (int u = 1; u <= unit; u++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				if (map[r][c] == 0) {
					for (int d = 3; d > 0; d--) {
						int nr = r + dr[dir - 1] * d;
						int nc = c + dc[dir - 1] * d;
						if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
							if (map[nr][nc] == 0) {
								map[nr][nc] = u;
								r = nr;
								c = nc;
							} else {
								ans = u;
							}
						} else {
							continue L;
						}
					}
				}
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
}
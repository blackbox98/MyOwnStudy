import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int R, C, ans;
	static int[] dr = { 0, -1 };
	static int[] dc = { -1, 0 };
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arr = br.readLine().toCharArray();
		R = arr[0] - '0';
		C = arr[2] - '0';
		map = new int[R][C];
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c) - '0';
			}
		}
		dfs(new boolean[R][C], 0, 0);
		System.out.println(ans);
	}
	
	private static void dfs(boolean[][] v, int sr, int sc) {
		if (sr == R) {
			result(v);
			return;
		}
		
		if (sc == C) {
			dfs(v, sr + 1, 0);
			return;
		}
		
		v[sr][sc] = true; // 가로
		dfs(v, sr, sc + 1);
		v[sr][sc] = false; // 세로
		dfs(v, sr, sc + 1);
	}

	private static void result(boolean[][] v) {
		boolean[][] check = new boolean[R][C];
		int total = 0;
		for (int r = R - 1; r >= 0; r--) {
			for (int c = C - 1; c >= 0; c--) {
				if (!check[r][c]) {
					if (v[r][c]) { // 가로
						int n = 1;
						for (int i = c; i >= 0; i--) {
							if (v[r][i]) {
								check[r][i] = true;
								total += map[r][i] * n;
								n *= 10;
							} else {
								break;
							}
						}
					} else { // 세로
						int n = 1;
						for (int i = r; i >= 0; i--) {
							if (!v[i][c]) {
								check[i][c] = true;
								total += map[i][c] * n;
								n *= 10;
							} else {
								break;
							}
						}
					}
				}
			}
		}
		ans = Math.max(ans, total);
	}
}
package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_17070 {

	static int N, ans = 0;
	static int[][] map;
	static int[] dr = { 0, 1, 1 }; // 우, 우하, 하
	static int[] dc = { 1, 1, 0 };
	static int[][] dir = { { 0, 1 }, { 1, 2 }, { 0, 1, 2 } }; // 가로, 세로, 대각선

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_17070_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		StringTokenizer st = null;
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(1, 1, 1, 2);
		System.out.println(ans);
	}

	private static void dfs(int sr, int sc, int er, int ec) {
		if (er == N && ec == N) {
			ans++;
			return;
		}
		int type = 0;
		if (er - sr == 0 && ec - sc == 1) { // 현재 파이프가 가로로 위치한 경우
			type = 0;
		} else if (er - sr == 1 && ec - sc == 0) { // 현재 파이프가 세로로 위치한 경우
			type = 1;
		} else if (er - sr == 1 && ec - sc == 1) { // 현재 파이프가 대각선으로 위치한 경우
			type = 2;
		}

		for (int d = 0; d < dir[type].length; d++) {
			int nr = er + dr[dir[type][d]];
			int nc = ec + dc[dir[type][d]];
			if (nr >= 1 && nr <= N && nc >= 1 && nc <= N && map[nr][nc] == 0) {
				if (dir[type][d] == 1 && er + 1 <= N && ec + 1 <= N && (map[er + 1][ec] == 1 || map[er][ec + 1] == 1)) { // 대각선으로 회전하는 경우
					continue;
				}
				dfs(er, ec, nr, nc);
			}
		}
	}
}
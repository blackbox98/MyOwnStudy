package edu.ssafy.chap09;

/*
4 5
50 45 37 32 30
35 50 40 20 25
30 30 25 17 28
27 24 22 15 10

// 3
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_1520 {

	static int M, N, ans;
	static int[][] map;
	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };
	static int[][] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		for (int r = 0; r < M; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
//		print(map);
//		dfs(0, 0); // 시간초과
		
		// 하향식 dp
		memo = new int[M][N];
		for (int r = 0; r < M; r++) {
			Arrays.fill(memo[r], -1);
		}
		ans = dfs_dp(0, 0);
		
		System.out.println(ans);
	}

	private static int dfs_dp(int r, int c) {
		if (r == M - 1 && c == N - 1) {
			return 1;
		}
		
		if(memo[r][c] != -1) {
			return memo[r][c];
		}
		
		memo[r][c] = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr >= 0 && nr < M && nc >= 0 && nc < N && map[r][c] > map[nr][nc]) {
				memo[r][c] = memo[r][c] + dfs_dp(nr, nc);
			}
		}
		
		return memo[r][c];
	}

	private static void dfs(int r, int c) {
		if (r == M - 1 && c == N - 1) {
			ans++;
		}
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr >= 0 && nr < M && nc >= 0 && nc < N && map[r][c] > map[nr][nc]) {
				dfs(nr, nc);
			}
		}
	}

	private static void print(int[][] map) {
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
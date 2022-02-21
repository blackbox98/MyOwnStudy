package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_2178 {

	static int N, M, ans = Integer.MAX_VALUE;
	static int[][] map;
	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_2178_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		for (int r = 1; r <= N; r++) {
			String str = br.readLine();
			for (int c = 1; c <= M; c++) {
				map[r][c] = str.charAt(c-1) - '0';
			}
		}
		maze(new boolean[N+1][M+1], 1, 1, 1);
		System.out.println(ans);
	}
	private static void maze(boolean[][] v, int r, int c, int cnt) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {r, c, cnt});
		v[r][c] = true;
		
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			if (current[0] == N && current[1] == M) {
				ans = Math.min(ans, current[2]);
			}
			for (int d = 0; d < 4; d++) {
				int nr = current[0] + dr[d];
				int nc = current[1] + dc[d];
				if (check(nr, nc) && !v[nr][nc] && map[nr][nc] == 1) {
					v[nr][nc] = true;
					queue.offer(new int[] {nr, nc, current[2] + 1});
				}
			}
		}
	}
	private static boolean check(int r, int c) {
		if (r > 0 && r <= N && c > 0 && c <= M) return true;
		else return false;
	}
}
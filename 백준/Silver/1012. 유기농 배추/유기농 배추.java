import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };
	static int[][] map;
	static boolean[][] v;
	static int M, N, K;
	static int Ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			Ans = 0;
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[M][N];
			v = new boolean[M][N];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;

			}

			for (int r = 0; r < M; r++) {
				for (int c = 0; c < N; c++) {
					if (!v[r][c] && map[r][c] == 1) {
						dfs(r, c);
						Ans++;
					}
				}
			}
			System.out.println(Ans);
		}
	}

	private static void dfs(int r, int c) {
		if (r == M - 1 && c == N - 1) {
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr >= 0 && nr < M && nc >= 0 && nc < N && !v[nr][nc] && map[nr][nc] == 1) {
				v[nr][nc] = true;
				dfs(nr, nc);
			}
		}
	}
}
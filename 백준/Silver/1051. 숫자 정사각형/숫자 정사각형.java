import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, Ans = 1;
	static int[][] map;
	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = str.charAt(c) - '0';
			}
		}
		// print(map);
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				check(r, c);
			}
		}
		System.out.println(Ans);
	}

	private static void check(int r, int c) {

		for (int d = 0; d < 2; d++) {
			int k = 1;
			while ((r+k) < N || (c + k) < M) {
				int nr = r + dr[d] * k;
				int nc = c + dc[d] * k;
				if (nr < N && nc < M) {
					if (map[r][c] == map[nr][nc] && d == 0) {
						int dif = nc - c;
						if ((r + dif) < N && (nr + dif) < N && map[r + dif][c] == map[r][c] && map[nr + dif][nc] == map[r][c]) {
							Ans = Math.max(Ans, (dif+1)*(dif+1));
						}
					}
					if (map[r][c] == map[nr][nc] && d == 1) {
						int dif = nr - r;
						if ((c + dif) < M && (nc + dif) < M && map[r][c + dif] == map[r][c] && map[nr][nc + dif] == map[r][c]) {
							Ans = Math.max(Ans, (dif+1)*(dif+1));
						}
					}
				}
				k++;
			}
		}
	}
	
	private static void print(int[][] map) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
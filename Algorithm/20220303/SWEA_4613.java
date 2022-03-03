package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4613 {

	static int N, M, ans;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_4613_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int tc = 1; tc <= T; tc++) {
			ans = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			
			for (int r = 0; r < N; r++) {
				map[r] = br.readLine().toCharArray();
			}
			draw();
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	private static void draw() {
		for (int w = 1; w <= N - 2; w++) {
			for (int b = 1; b <= N - 2; b++) {
				for (int r = 1; r <= N - 2; r++) {
					int sum = w + b + r;
					if(sum == N) {
						ans = Math.min(ans, count(w, b, r));
					}
				}
			}
		}
	}

	private static int count(int w, int b, int r) {
		int cnt = 0;
		int row = w;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] != 'W') {
					cnt++;
				}
			}
		}
		for (int i = row; i < row + b; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] != 'B') {
					cnt++;
				}
			}
		}
		row += b;
		for (int i = row; i < row + r; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] != 'R') {
					cnt++;
				}
			}
		}
		return cnt;
	}
}
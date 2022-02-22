package hw_20220222;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1220 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_1220_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			int ans = 0;
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			for (int c = 0; c < N; c++) {
				boolean flag = false;
				for (int r = 0; r < N; r++) {
					if (map[r][c] == 1) {
						flag = true;
					}
					if (flag && map[r][c] == 2) {
						ans++;
						flag = false;
					}
				}
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
}
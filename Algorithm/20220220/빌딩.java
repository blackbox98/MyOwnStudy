package Daily_Practice;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빌딩 {

	static int ans;
	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/building_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = 2; // 맵에 B가 하나 이상 있다고 가정
			int N = Integer.parseInt(br.readLine());
			char[][] map = new char[N][N];
			boolean check = false; // 전체 맵에 B가 하나도 없는 경우에 대한 처리
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = st.nextToken().charAt(0);
					if (map[r][c] == 'B') {
						check = true;
					}
				}
			}
			if (check) {
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						boolean flag = true;
						if (map[r][c] == 'B') {
							for (int i = 0; i < dr.length; i++) {
								int nr = r + dr[i];
								int nc = c + dc[i];
								if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 'G') {
									flag = false;
								}
							}
							if (flag) {
								int cnt = 0;
								for (int i = 0; i < map.length; i++) {
									if (map[r][i] == 'B') {
										cnt++;
									}
									if (map[i][c] == 'B') {
										cnt++;
									}
								}
								ans = Math.max(ans, cnt) - 1;
							}
						}

					}
				}
			} else {
				ans = 0;
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	private static void print(char[][] map) {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map.length; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
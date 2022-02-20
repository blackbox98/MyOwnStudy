package Daily_Practice;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 점프_사방 {

	static int ans;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/점프_사방.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int Y = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			ans = N * -1000;
			String[][] map = new String[Y + 1][X + 1];
			for (int r = 1; r <= Y; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 1; c <= X; c++) {
					map[r][c] = st.nextToken();
				}
			}
			Point[] p = new Point[N];
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				p[n] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()));

			}
			
			String[] str = br.readLine().split(" ");
			for (int i = 0; i < Integer.parseInt(str[0]); i++) {
				for (int j = 1; j < Integer.parseInt(str[0]) * 2; j+=2) {
					map[Integer.parseInt(str[j])][Integer.parseInt(str[j+1])] = "0";
				}
			}
			
			for (int i = 0; i < N; i++) {
				int r = p[i].r;
				int c = p[i].c;
				int num = p[i].num;
				if (map[r][c] != "0") {
					for (int n = 1; n <= num; n++) {
						int dir = map[r][c].charAt(0) - '0';
						int k = map[r][c].charAt(1) - '0';
						int nr = r + dr[dir-1] * k;
						int nc = c + dc[dir-1] * k;
						if (nr > 0 && nr <= Y && nc > 0 && nc <= X && map[nr][nc] != "0") {
							if (n == num) {
								
								ans += Integer.parseInt(map[nr][nc]) * 100;
							}
							r = nr;
							c = nc;
						} else {
							break;
						}
					}
				}
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	static class Point {
		int r, c, num;

		Point(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}
}
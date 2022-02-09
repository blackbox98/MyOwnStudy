package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1210 {

	static int T;
	static int Ans;
	static boolean goal;
	static int[] dc = { -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("InOutFiles/SWEA_1210_input.txt")));
		for (int tc = 1; tc <= 10; tc++) {
			Ans = 0;
			goal = false; // 목표지점에 도달한 경우 true로 변경
			int x = 0, y = 0;
			T = Integer.parseInt(br.readLine());
			char[][] map = new char[100][100];
			for (int r = 0; r < 100; r++) {
				String[] str = br.readLine().split(" ");
				for (int c = 0; c < 100; c++) {
					map[r][c] = str[c].charAt(0);
					if (map[r][c] == '2') {
						x = r;
						y = c;
					}
				}
			}
			int way = 0; // 0 : 좌, 1 : 우
			int k = 1;
			int nc;
			while (!goal) {
				nc = y + dc[way] * k;
				if (nc >= 0 && nc < 100 && map[x][nc] == '1') {
					k++;
				}
				else {
					y = nc - dc[way];
					way++;
					if (way > 1 || k != 1) {
						way = 0;
						x--;
					}
					k = 1;
					if (x == 0) {
						goal = true;
						Ans = y;
						break;
					}
				}
			}
			System.out.printf("#%d %d\n", T, Ans);
		}
	}
}
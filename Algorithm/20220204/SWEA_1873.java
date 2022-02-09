package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1873 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("InOutFiles/SWEA_1873_input.txt")));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String[] rcStr = br.readLine().split(" ");
			int H = Integer.parseInt(rcStr[0]);
			int W = Integer.parseInt(rcStr[1]);
			char[][] map = new char[H][W];
			int r = 0, c = 0;
			int direction = 0;
			for (int i = 0; i < map.length; i++) {
				String strMap = br.readLine();
				for (int j = 0; j < map[i].length; j++) {
					map[i][j] = strMap.charAt(j);
					if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
						switch (map[i][j]) {
						case '^':
							direction = 1;
							break;
						case 'v':
							direction = 2;
							break;
						case '<':
							direction = 3;
							break;
						case '>':
							direction = 4;
							break;
						default:
							break;
						}
						r = i;
						c = j;
					}
				}
			}
			int N = Integer.parseInt(br.readLine());
			String strMove = br.readLine();
			for (int m = 0; m < strMove.length(); m++) {
				switch (strMove.charAt(m)) {
				case 'U':
					if (r - 1 >= 0 && map[r - 1][c] == '.') {
						map[r - 1][c] = '^';
						map[r][c] = '.';
						r -= 1;
						direction = 1;
					} else {
						map[r][c] = '^';
						direction = 1;
					}
					break;
				case 'D':
					if (r + 1 < H && map[r + 1][c] == '.') {
						map[r + 1][c] = 'v';
						map[r][c] = '.';
						r += 1;
						direction = 2;
					} else {
						map[r][c] = 'v';
						direction = 2;
					}
					break;
				case 'L':
					if (c - 1 >= 0 && map[r][c - 1] == '.') {
						map[r][c - 1] = '<';
						map[r][c] = '.';
						c -= 1;
						direction = 3;
					} else {
						map[r][c] = '<';
						direction = 3;
					}
					break;
				case 'R':
					if (c + 1 < W && map[r][c + 1] == '.') {
						map[r][c + 1] = '>';
						map[r][c] = '.';
						c += 1;
						direction = 4;
					} else {
						map[r][c] = '>';
						direction = 4;
					}
					break;
				case 'S':
					switch (direction) {
					case 1:
						for (int s = r - 1; s >= 0; s--) {
							if (map[s][c] == '*') {
								map[s][c] = '.';
								break;
							}
							if (map[s][c] == '#') {
								break;
							}
						}
						break;
					case 2:
						for (int s = r + 1; s < H; s++) {
							if (map[s][c] == '*') {
								map[s][c] = '.';
								break;
							}
							if (map[s][c] == '#') {
								break;
							}
						}
						break;
					case 3:
						for (int s = c - 1; s >= 0; s--) {
							if (map[r][s] == '*') {
								map[r][s] = '.';
								break;
							}
							if (map[r][s] == '#') {
								break;
							}
						}
						break;
					case 4:
						for (int s = c + 1; s < W; s++) {
							if (map[r][s] == '*') {
								map[r][s] = '.';
								break;
							}
							if (map[r][s] == '#') {
								break;
							}
						}
						break;
					default:
						break;
					}
					break;
				default:
					break;
				}
			}
			System.out.print("#" + tc + " ");
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}
}
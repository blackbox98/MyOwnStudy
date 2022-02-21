package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_4047 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_4047_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String str = br.readLine();
			boolean[][] cards = new boolean[4][14];
			boolean flag = true;
			L: for (int i = 0; i < str.length(); i += 3) {
				char p = str.charAt(i);
				int n = Integer.parseInt((str.substring(i + 1, i + 3)));
				switch (p) {
				case 'S':
					if (!cards[0][n]) {
						cards[0][n] = true;
					} else {
						flag = false;
						break L;
					}
					break;
				case 'D':
					if (!cards[1][n]) {
						cards[1][n] = true;
						flag = true;
					} else {
						flag = false;
						break L;
					}
					break;
				case 'H':
					if (!cards[2][n]) {
						cards[2][n] = true;
						flag = true;
					} else {
						flag = false;
						break L;
					}
					break;
				case 'C':
					if (!cards[3][n]) {
						cards[3][n] = true;
						flag = true;
					} else {
						flag = false;
						break L;
					}
					break;
				default:
					flag = false;
				}
			}
			if (flag) {
				System.out.print("#" + tc + " ");
				for (int i = 0; i < 4; i++) {
					int cnt = 0;
					for (int j = 1; j < 14; j++) {
						if (cards[i][j]) {
							cnt++;
						}
					}
					System.out.print((13 - cnt) + " ");
				}
				System.out.println();
			} else {
				System.out.println("#" + tc + " ERROR");
			}
		}
	}
}
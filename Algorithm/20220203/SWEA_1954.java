package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1954 {

	public static void main(String[] args) throws NumberFormatException, IOException, Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("InOutFiles/SWEA_1954_input.txt")));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] snail = new int[N][N];
			if (N != 1) {
				int num = 1;
				int r = 0, d = 0;
				int limit = 0;
				int changeDR = 1;
				while (snail[r][d] == 0) {
					snail[r][d] = num;
					if (changeDR == 1) {
						d++;
						if (d == N) {
							changeDR++;
							r++;
							d--;
						}
					} else if (changeDR == 2) {
						r++;
						if (r == N) {
							changeDR++;
							N--;
							r--;
							d--;
						}
					} else if (changeDR == 3) {
						d--;
						if (d == limit - 1) {
							changeDR++;
							limit++;
							r--;
							d++;
						}
					} else if (changeDR == 4) {
						r--;
						if (r == limit - 1) {
							changeDR = 1;
							r++;
							d++;
						}
					}
					num++;
				}
				System.out.println("#" + tc);
				for (int i = 0; i < snail.length; i++) {
					for (int j = 0; j < snail.length; j++) {
						System.out.print(snail[i][j] + " ");
					}
					System.out.println();
				}
			} else {
				System.out.println("#" + tc + "\n1");
			}
		}
	}
}
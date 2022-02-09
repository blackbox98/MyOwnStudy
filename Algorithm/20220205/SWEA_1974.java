package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1974 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("InOutFiles/SWEA_1974_input.txt")));
		int T = Integer.parseInt(br.readLine());
		int[] check = new int[9];
		for (int tc = 1; tc <= T; tc++) {
			int[][] num = new int[9][9];
			int result = 1;
			for (int i = 0; i < 9; i++) {
				String str = br.readLine();
				StringTokenizer strTk = new StringTokenizer(str);
				for (int j = 0; j < 9; j++) {
					num[i][j] = Integer.parseInt(strTk.nextToken());
				}
			}
			// 가로, 세로 검사
			if (result == 1) {
				for (int i = 0; i < 9; i++) {
					// 가로줄 검사
					for (int j = 0; j < 9; j++) {
						check[num[i][j] - 1]++;
					}
					for (int k = 0; k < 9; k++) {
						if (check[k] != 1) {
							result = 0;
							break;
						}
					}
					Arrays.fill(check, 0);
					// 세로줄 검사
					for (int j = 0; j < 9; j++) {
						check[num[j][i] - 1]++;
					}
					for (int k = 0; k < 9; k++) {
						if (check[k] != 1) {
							result = 0;
							break;
						}
					}
					Arrays.fill(check, 0);
					if (result == 0) {
						break;
					}
				}
			}
			// 3*3 구역 검사
			if (result == 1) {
				for (int i = 0; i < 9; i+=3) {
					for (int j = 0; j < 9; j+=3) {
						for (int r = i; r < i+3; r++) {
							for (int c = j; c < j+3; c++) {
								check[num[r][c] - 1]++;
							}
						}
						for (int k = 0; k < 9; k++) {
							if (check[k] != 1) {
								result = 0;
								break;
							}
						}
						Arrays.fill(check, 0);
						if (result == 0) {
							break;
						}
					}
					if (result == 0) {
						break;
					}
				}
			}
			System.out.println("#" + tc + " "+ result);
		}
	}
}
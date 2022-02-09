package SWEA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_2805 {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_2805_input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int sum = 0;
			int N = sc.nextInt();
			int center = N / 2;
			int[][] map = new int[N][N];
			for (int i = 0; i < map.length; i++) {
				String sNum = sc.next();
				for (int j = 0; j < map.length; j++) {
					map[i][j] = sNum.charAt(j) - '0';
					int diff = Math.abs(center - i);
					if (i == center) {
						sum += map[i][j];
					} else if (i == center + diff | i == center - diff) {
						if (j >= diff && j < N - diff) {
							sum += map[i][j];
						}
					}
				}
			}
			System.out.println("#" + tc + " " + sum);
		}
	}
}

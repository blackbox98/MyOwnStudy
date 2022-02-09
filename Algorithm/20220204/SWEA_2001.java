package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2001 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("InOutFiles/SWEA_2001_input.txt")));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String[] str = br.readLine().split(" ");
			int N = Integer.parseInt(str[0]);
			int M = Integer.parseInt(str[1]);
			int[][] map = new int[N][N];
			for (int i = 0; i < map.length; i++) {
				StringTokenizer flies = new StringTokenizer(br.readLine());
				for (int j = 0; j < map[i].length; j++) {
					map[i][j] = Integer.parseInt(flies.nextToken());
				}
			}
			int sum = 0;
			int best = Integer.MIN_VALUE;
			for (int r = 0; r <= N - M; r++) {
				for (int c = 0; c <= N - M; c++) {
					for (int i = r; i < r + M; i++) {
						for (int j = c; j < c + M; j++) {
							sum += map[i][j];
						}
					}
					best = Math.max(best, sum);
					sum = 0;
				}
			}
			System.out.printf("#%d %d\n", tc, best);
		}
	}
}
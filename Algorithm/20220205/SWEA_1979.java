package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1979 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("InOutFiles/SWEA_1979_input.txt")));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int result = 0;
			String[] str = br.readLine().split(" ");
			int N = Integer.parseInt(str[0]);
			int K = Integer.parseInt(str[1]);
			int[][] puzzle = new int[N][N];
			for (int i = 0; i < puzzle.length; i++) {
				StringTokenizer strTk = new StringTokenizer(br.readLine());
				for (int j = 0; j < puzzle.length; j++) {
					puzzle[i][j] = Integer.parseInt(strTk.nextToken());
				}
			}
			int limit = N - K;
			for (int r = 0; r < N; r++) {
				C1: for (int c = 0; c < N; c++) {
					if (c == 0 && puzzle[r][c] == 1 && (c + K) < N && puzzle[r][c + K] == 0) {
						for (int ck = c; ck < c + K; ck++) {
							if (puzzle[r][ck] == 0) {
								continue C1;
							}
						}
						result++;
					} else if (c == limit && puzzle[r][c] == 1 && (c - 1) >= 0 && puzzle[r][c - 1] == 0) {
						for (int ck = c; ck < c + K; ck++) {
							if (puzzle[r][ck] == 0) {
								break C1;
							}
						}
						result++;
					} else if (puzzle[r][c] == 1 && (c + K) < N && puzzle[r][c + K] == 0 && (c - 1) >= 0
							&& puzzle[r][c - 1] == 0) {
						for (int ck = c; ck < c + K; ck++) {
							if (puzzle[r][ck] == 0) {
								continue C1;
							}
						}
						result++;
					}
				}
				C2: for (int c = 0; c < N; c++) {
					if (c == 0 && puzzle[c][r] == 1 && (c + K) < N && puzzle[c + K][r] == 0) {
						for (int ck = c; ck < c + K; ck++) {
							if (puzzle[ck][r] == 0) {
								continue C2;
							}
						}
						result++;
					} else if (c == limit && puzzle[c][r] == 1 && (c - 1) >= 0 && puzzle[c - 1][r] == 0) {
						for (int ck = c; ck < c + K; ck++) {
							if (puzzle[ck][r] == 0) {
								break C2;
							}
						}
						result++;
					} else if (puzzle[c][r] == 1 && (c + K) < N && puzzle[c + K][r] == 0 && (c - 1) >= 0
							&& puzzle[c - 1][r] == 0) {
						for (int ck = c; ck < c + K; ck++) {
							if (puzzle[ck][r] == 0) {
								continue C2;
							}
						}
						result++;
					}
				}
			}
			System.out.printf("#%d %d\n", tc, result);
			result = 0;
		}
	}
}
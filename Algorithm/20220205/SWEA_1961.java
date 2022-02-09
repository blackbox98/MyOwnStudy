package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1961 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("InOutFiles/SWEA_1961_input.txt")));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] num = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer str = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					num[i][j] = Integer.parseInt(str.nextToken());
				}
			}
			System.out.println("#" + tc);
			for (int i = 0; i < N; i++) {
				for (int j = N-1; j >= 0; j--) {
					System.out.print(num[j][i]);
				}
				System.out.print(" ");
				for (int j = N-1; j >= 0; j--) {
					System.out.print(num[N-i-1][j]);
				}
				System.out.print(" ");
				for (int j = 0; j < N; j++) {
					System.out.print(num[j][N-i-1]);
				}
				System.out.println();
			}
		}
	}
}
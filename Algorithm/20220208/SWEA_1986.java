package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1986 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("InOutFiles/SWEA_1986_input.txt")));
		int T = Integer.parseInt(br.readLine());
		int sum;
		for (int tc = 1; tc <= T; tc++) {
			sum = 0;
			int N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				if (i % 2 == 0) {
					sum += (i + 1);
				} else {
					sum -= (i + 1);
				}
			}
			System.out.printf("#%d %d\n", tc, sum);
		}
	}
}
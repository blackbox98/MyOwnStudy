package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1984 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("InOutFiles/SWEA_1984_input.txt")));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			int sum = 0;
			int[] num = new int[10];
			for (int i = 0; i < 10; i++) {
				num[i] = Integer.parseInt(str.nextToken());
			}
			Arrays.sort(num);
			for (int j = 1; j < 9; j++) {
				sum += num[j];
			}
			System.out.printf("#%d %.0f\n", tc, sum / 8.0);
		}
	}
}
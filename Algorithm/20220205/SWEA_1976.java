package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1976 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("InOutFiles/SWEA_1976_input.txt")));
		int T = Integer.parseInt(br.readLine());
		int[] result = new int[2];
		for (int tc = 1; tc <= T; tc++) {
			String[] str = br.readLine().split(" ");
			int[] time = new int[str.length];
			for (int i = 0; i < time.length; i++) {
				time[i] = Integer.parseInt(str[i]);
			}
			result[0] = time[0] + time[2];
			if (result[0] > 12) {
				result[0] -= 12;
			}
			result[1] = time[1] + time[3];
			if (result[1] >= 60) {
				result[0]++;
				result[1] -= 60;
			}
			System.out.printf("#%d %d %d\n", tc, result[0], result[1]);
		}
	}
}
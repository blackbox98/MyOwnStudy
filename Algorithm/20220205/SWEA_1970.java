package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1970 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("InOutFiles/SWEA_1970_input.txt")));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int won = Integer.parseInt(br.readLine());
			int[] don = { 50000, 10000, 5000, 1000, 500, 100, 50, 10 };
			int[] result = new int[don.length];
			for (int i = 0; i < don.length; i++) {
				if (won/don[i] > 0) {
					result[i] = won/don[i];
					won %= don[i];
				}
			}
			System.out.println("#" + tc);
			for (int i : result) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
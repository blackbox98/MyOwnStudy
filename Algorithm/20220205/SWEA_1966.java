package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_1966 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("InOutFiles/SWEA_1966_input.txt")));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] num = new int[N];
			String[] str = br.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(str[i]);
			}
			Arrays.sort(num);
			System.out.print("#" + tc);
			for (int i : num) {
				System.out.print(" " + i);
			}
			System.out.println();
		}
	}
}
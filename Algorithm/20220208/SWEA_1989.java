package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1989 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("InOutFiles/SWEA_1989_input.txt")));
		int T = Integer.parseInt(br.readLine());
		int center, result;
		String str;
		for (int tc = 1; tc <= T; tc++) {
			str = br.readLine();
			result = 1;
			center = str.length() / 2;
			for (int i = 0; i < center; i++) {
				if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
					result = 0;
					break;
				}
			}
			System.out.printf("#%d %d\n", tc, result);
		}
	}
}
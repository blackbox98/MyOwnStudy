package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_2007 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_2007_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String str;
		char start, end = ' ', front = ' ';
		for (int tc = 1; tc <= T; tc++) {
			int Ans = 0;
			boolean check = false;
			str = br.readLine();
			start = str.charAt(0);
			for (int i = 1; i < str.length(); i++) {
				if (str.charAt(i) == start) {
					if (!check) {
						end = str.charAt(i - 1);
						front = str.charAt(i + 1);
						Ans = i;
						check = true;
					} else {
						if (front != str.charAt(i + 1) && end != str.charAt(i - 1)) {
							Ans = i;
						}
						break;
					}
				}
			}
			System.out.printf("#%d %d\n", tc, Ans);
		}
	}
}
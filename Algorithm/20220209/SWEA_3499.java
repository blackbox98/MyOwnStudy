package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_3499 {

	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("InOutFiles/SWEA_3499_input.txt")));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			sb = new StringBuilder();
			String[] strArr = br.readLine().split(" ");
			int center = strArr.length / 2;
			boolean flag = (strArr.length % 2 == 0)? true : false;
			for (int i = 0; i < center; i++) {
				sb.append(strArr[i] + " ");
				if (flag) {
					sb.append(strArr[i + center] + " ");
				} else {
					sb.append(strArr[i + center + 1] + " ");
					if (i == center - 1) {
						sb.append(strArr[center] + " ");
					}
				}
			}

			System.out.printf("#%d %s\n", tc, sb);
		}
	}
}
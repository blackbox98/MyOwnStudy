package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1206 {

	static int Ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("InOutFiles/SWEA_1206_input.txt")));
		for (int tc = 1; tc <= 10; tc++) {
			Ans = 0;
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] bd = new int[N];
			for (int i = 0; i < N; i++) {
				bd[i] = Integer.parseInt(st.nextToken());
				if (i >= 4) {
					if ((bd[i - 2] - bd[i - 3]) > 0 && (bd[i - 2] - bd[i - 1]) > 0 && (bd[i - 2] - bd[i - 4]) > 0 && (bd[i - 2] - bd[i]) > 0) {
						Ans += Math.min(Math.min((bd[i - 2] - bd[i - 3]), (bd[i - 2] - bd[i - 1])), Math.min((bd[i - 2] - bd[i - 4]), (bd[i - 2] - bd[i])));
					}
				}
			}
			System.out.printf("#%d %d\n", tc, Ans);
		}
	}
}
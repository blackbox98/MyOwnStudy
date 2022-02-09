package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_6730 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("InOutFiles/SWEA_6730_input.txt")));
		int T = Integer.parseInt(br.readLine());
		int N;
		int up;
		int down;
		int[] block;
		for (int tc = 1; tc <= T; tc++) {
			up = 0;
			down = 0;
			N = Integer.parseInt(br.readLine());
			block = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			block[0] = Integer.parseInt(st.nextToken());
			for (int i = 1; i < N; i++) {
				block[i] = Integer.parseInt(st.nextToken());
				if(block[i] > block[i-1]) {
					up = Math.max(up, Math.abs(block[i] - block[i-1]));
				} else {
					down = Math.max(down, Math.abs(block[i] - block[i-1]));
				}
			}
			System.out.printf("#%d %d %d\n", tc, up, down);
		}
	}
}
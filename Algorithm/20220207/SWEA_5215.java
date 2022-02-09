package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5215 {

	static int N, L;
	static int result;
	static int[][] food;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("InOutFiles/SWEA_5215_input.txt")));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			N = Integer.parseInt(str.nextToken());
			L = Integer.parseInt(str.nextToken());
			food = new int[N][2];
			result = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				StringTokenizer strFood = new StringTokenizer(br.readLine());
				food[i][0] = Integer.parseInt(strFood.nextToken());
				food[i][1] = Integer.parseInt(strFood.nextToken());
			}
			burger(0, 0, 0);
			System.out.printf("#%d %d\n", tc, result);
		}
	}
	
	public static void burger(int cnt, int score, int totalCal) {
		if (totalCal > L) {
			return;
		}
		if (cnt == N) {
			result = Math.max(result, score);
			return;
		}
		burger(cnt + 1, score + food[cnt][0], totalCal + food[cnt][1]);
		burger(cnt + 1, score, totalCal);
	}
}
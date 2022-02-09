package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class SWEA_1983 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("InOutFiles/SWEA_1983_input.txt")));
		String[] grade = { "A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0" };
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String[] str = br.readLine().split(" ");
			int N = Integer.parseInt(str[0]);
			int K = Integer.parseInt(str[1]);
			Double[] stuScore = new Double[N];
			String[] stuGrade = new String[N];
			for (int i = 0; i < N; i++) {
				String[] score = br.readLine().split(" ");
				stuScore[i] = (Integer.parseInt(score[0]) * 0.35) + (Integer.parseInt(score[1]) * 0.45)
						+ (Integer.parseInt(score[2]) * 0.2);
			}
			Double KScore = stuScore[K - 1];
			Arrays.sort(stuScore, Collections.reverseOrder());
			int cnt = 0;
			int idx = 0;
			L: for (int i = 0; i < N; i += N / 10) {
				for (int j = i; j < i + (N / 10); j++) {
					stuGrade[j] = grade[cnt];
					if (stuScore[j] == KScore) {
						idx = j;
						break L;
					}
				}
				cnt++;
			}
			System.out.printf("#%d %s\n", tc, stuGrade[idx]);
		}
	}
}
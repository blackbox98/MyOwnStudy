package hw_20220214;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_6808 {

	static List<Integer> GCards;
	static List<Integer> ICards;
	static int win, lose;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_6808_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			win = 0;
			lose = 0;
			GCards = new ArrayList<>();
			ICards = new ArrayList<>();
			for (int i = 1; i <= 18; i++) {
				ICards.add(i);
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				GCards.add(Integer.parseInt(st.nextToken()));
				ICards.remove(GCards.get(i));
			}
			game(new int[9], 0, 0);

			System.out.printf("#%d %d %d\n", tc, win, lose);
		}
	}

	private static void game(int[] selList, int cnt, int flag) {
		if (cnt == selList.length) {
			int sumG = 0;
			int sumI = 0;
			for (int i = 0; i < selList.length; i++) {
				if (GCards.get(i) > selList[i]) {
					sumG += selList[i] + GCards.get(i);
				} else if (GCards.get(i) < selList[i]) {
					sumI += selList[i] + GCards.get(i);
				}
			}
			if (sumG > sumI) {
				win++;
			} else if (sumG < sumI) {
				lose++;
			}
			return;
		}
		for (int i = 0; i < ICards.size(); i++) {
			if ((flag & 1 << i) == 0) {
				selList[i] = ICards.get(cnt);
				game(selList, cnt + 1, flag | 1 << i);
			}
		}
	}
}
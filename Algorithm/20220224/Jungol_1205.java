package hw_20220224;

/*
 * 아직 마무리 하지 못한 코드입니다... 추후에 완성하여 다시 제출하겠습니다!
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Jungol_1205 {

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("InOutFiles/Jungol_1205_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> cards = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int zero = 0;
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			if (n == 0) {
				zero++;
				continue;
			}
			cards.add(n);
		}
		Collections.sort(cards);
		System.out.println("=====" + cards); //
		int start = cards.get(0);
		cards.remove(0);
		int end = 0;
		ArrayList<Integer> result = new ArrayList<>();
		result.add(start);
		while (!cards.isEmpty()) {
			int num = cards.get(0);
			if (result.get(result.size() - 1) == num) {
				System.out.println("same"); //
				cards.remove(0);
				continue;
			} else if (result.get(result.size() - 1) + 1 == num) {
				result.add(num);
				cards.remove(0);
				System.out.println(num + " : add"); //
			} else if (result.get(result.size() - 1) + 1 < num) {
				if (zero > 0) {
					if (num <= result.get(result.size() - 1) + 1 + zero) {
						result.add(result.get(result.size() - 1) + 1);
						zero--;
						System.out.println(result.get(result.size() - 1) + " : add use Zero"); //
						continue;
					}
				}
				if (result.size() == 1) {
					result.remove(0);
					result.add(cards.get(0));
					cards.remove(0);
				} else {
					cards.remove(0);
				}
			}
		}
		System.out.println(result); //
		System.out.println(result.size() + zero);
	}
}
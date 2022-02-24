package hw_20220224;

/* 백준 사이트에서 제공되는 테스트 케이스와 MM에 올려주신 KMP_input.txt의 결과도 알맞게 나오지만
 * 사이트에 제출하였을 때 틀렸다고 나오는 상황입니다. 현재 코드는 교수님께서 올려주신 String_KMPTest.java 파일을 참고하지 않고 혼자 만들어 본 버전입니다.
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BaekJoon_1786 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/BaekJoon_1786_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String T = br.readLine();
		String p = br.readLine();
		int cnt = 0;
		ArrayList<Integer> idx = new ArrayList<>();
		int[] P = new int[p.length() + 1];
		for (int j = 0; j < p.length(); j++) {
			String str = p.substring(0, j+1);
			for (int c = 0; c < str.length()/2; c++) {
				if (str.charAt(c) == str.charAt(str.length() - 1)) {
					boolean goal = true;
					for (int k = 0; k < c; k++) {
						if (str.charAt(k) != str.charAt(str.length() - c - 1 + k)) {
							goal = false;
						}
					}
					if (goal) {
						P[j + 1] = c + 1;
					}
				}
			}
		}
		int i = 0;
		for (int j = 0; j < T.length(); j++) {
			if (T.charAt(j) == p.charAt(i)) {
				if (i == p.length() - 1) {
					cnt++;
					idx.add(j - i + 1);
					i = 0;
					j -= i + 2;
				} else {
					i++;
				}
			} else {
				i = P[i];
			}
		}

		System.out.println(cnt);
		for (int k = 0; k < idx.size(); k++) {
			System.out.print(idx.get(k) + " ");
		}
	}
}
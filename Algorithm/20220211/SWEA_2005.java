package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SWEA_2005 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_2005_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		List<Integer> list = new ArrayList<Integer>();
		List<Integer> check = new ArrayList<Integer>();
		for (int tc = 1; tc <= T; tc++) {
			System.out.println("#" + tc);
			int N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= i; j++) {
					if (j > 0 && j < i) {
						list.add(check.get(j - 1) + check.get(j));
					} else {
						list.add(1);
					}
				}
				System.out.println(list.toString().replaceAll("[\\[\\,\\]]", ""));
				check.clear();
				check.addAll(list);
				list.clear();
			}
		}
	}
}
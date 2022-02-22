package hw_20220222;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_1859 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_1859_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			long ans = 0l;
			int N = Integer.parseInt(br.readLine());
			int[] price = new int[N];
			int top = Integer.MIN_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				price[i] = Integer.parseInt(st.nextToken());
				top = Math.max(top, price[i]);
			}
			ArrayList<Integer> list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if (price[i] < top) {
					list.add(price[i]);
				} else {
					top = Integer.MIN_VALUE;
					while (!list.isEmpty()) {
						ans += price[i] - list.get(0);
						list.remove(0);
					}
					for (int j = i + 1; j < N; j++) {
						top = Math.max(top, price[j]);
					}
				}
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
}
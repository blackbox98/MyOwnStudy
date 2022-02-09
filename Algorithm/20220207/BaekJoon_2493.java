package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_2493 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("InOutFiles/BaekJoon_2493_input.txt")));
		int N = Integer.parseInt(br.readLine());
		ArrayList<int[]> num = new ArrayList<>();
		int n;
		StringTokenizer str = new StringTokenizer(br.readLine());
		num.add(new int[] {Integer.parseInt(str.nextToken()), 1});
		System.out.print(0 + " ");
		int big = num.get(0)[0];
		for (int i = 1; i < N; i++) {
			n = Integer.parseInt(str.nextToken());
			if (big < n) {
				num.clear();
				num.add(new int[] {n, i + 1});
				big = n;
				System.out.print(0 + " ");
				continue;
			} else {
				while (n > num.get(num.size() - 1)[0]) {
					num.remove(num.size() - 1);
				}
				System.out.print(num.get(num.size() - 1)[1] + " ");
				num.add(new int[] {n, i + 1});
			}
		}
	}
}
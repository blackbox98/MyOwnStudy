package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1527 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cnt = 0;
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		for (int i = s; i <= e; i++) {
			int num = i;
			boolean goal = false;
			while (num % 10 == 4 || num % 10 == 7) {
				goal = true;
				num /= 10;
			}
			if (goal && num == 0) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
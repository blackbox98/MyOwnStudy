package SWEA;

/*
 * 입출력 예시
 * input : 10
 * output : 1 2 - 4 5 - 7 8 - 10
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1926 {

	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			cnt = 0;
			if (check(i)) {
				for (int j = 0; j < cnt; j++) {
					System.out.print("-");
				}
				System.out.print(" ");
				continue;
			}
			System.out.print(i + " ");
		}
	}

	private static boolean check(int i) {
		while (i > 0) {
			int result = i % 10;
			i /= 10;
			if (result == 3 || result == 6 || result == 9) {
				cnt++;
			}
		}
		if (cnt == 0) {
			return false;
		} else {
			return true;
		}
	}
}
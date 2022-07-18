import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int num = str.length();
		int r = Integer.MIN_VALUE;
		int c = 0;
		for (int i = 1; i <= num; i++) {
			if (num % i == 0 && i <= num / i) {
				r = Math.max(i, r);
				c = num / i;
			}
		}
		char[][] msg = new char[r][c];
		int idx = 0;
		for (int i = 0; i < c; i++) {
			for (int j = 0; j < r; j++) {
				msg[j][i] = str.charAt(idx);
				idx++;
			}
		}
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(msg[i][j]);
			}
		}
	}
}
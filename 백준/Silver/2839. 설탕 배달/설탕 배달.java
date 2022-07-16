import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[] pack = { 5, 3 };
	static int Ans = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		sugar(N);
		System.out.println(Ans);
	}

	private static void sugar(int s) {
		while (s >= 3) {
			if ((s % pack[0]) == 0) {
				Ans += s / pack[0];
				s %= pack[0];
				break;
			} else {
				s -= pack[1];
				Ans++;
			}
		}
		if (s == 0) {
			return;
		} else {
			Ans = -1;
		}
	}
}
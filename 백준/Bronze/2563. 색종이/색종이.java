import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int Ans = 0;
		StringTokenizer st;
		int[][] map = new int[100][100];
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			for (int i = r - 1; i < r + 9; i++) {
				for (int j = c - 1; j < c + 9; j++) {
					if (map[i][j] == 0) {
						map[i][j] = 1;
						Ans++;
					}
				}
			}
		}
		System.out.println(Ans);
	}
}
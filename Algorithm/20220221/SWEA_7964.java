package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7964 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_7964_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int ans = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			String[] strArr = br.readLine().split(" ");
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (strArr[i].equals("0")) {
					cnt++;
				}
				if (strArr[i].equals("1")) {
					cnt = 0;
				}
				if (cnt >= D) {
					ans++;
					cnt = 0;
				}
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
}
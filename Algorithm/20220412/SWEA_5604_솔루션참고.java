package hw_20220412;
/*
 * 솔루션 참고
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5604_솔루션참고 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_5604_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			long ans = 0;
			st = new StringTokenizer(br.readLine());
			long A = Long.parseLong(st.nextToken());
			long B = Long.parseLong(st.nextToken());
			long[] arr = new long[10];
			long point = 1L;
			while (A <= B) {
				while (B % 10 != 9 && A <= B) {
					getNum(B, arr, point);
					B--;
				}
				if (B < A) {
					break;
				}
				while (A % 10 != 0 && A <= B) {
					getNum(A, arr, point);
					A++;
				}
				A /= 10L;
				B /= 10L;
				for (int i = 0; i < 10; i++) {
					arr[i] += (B - A + 1) * point;
				}
				point *= 10L;
			}
			
			for (int i = 0; i < 10; i++) {
				ans += (arr[i] * i);
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
	
	public static void getNum(long lnum, long[] arr, long point) {
		while (lnum > 0) {
			int num = (int) (lnum % 10L);
			arr[num] += point;
			lnum /= 10L;
		}
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] num;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		num = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);
		combination(new int[M], new boolean[N], 0, 0);
	}
	private static void combination(int[] sel, boolean[] v, int idx, int k) {
		if (k == sel.length) {
			for (int i = 0; i < sel.length; i++) {
				System.out.print(sel[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i = idx; i < v.length; i++) {
			if(!v[i]) {
				v[i] = true;
				sel[k] = num[i];
				combination(sel, v, i + 1, k + 1);
				v[i] = false;
			}
		}
	}
}
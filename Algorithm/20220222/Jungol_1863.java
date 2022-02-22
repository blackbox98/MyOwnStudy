package hw_20220222;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jungol_1863 {

	static int n, m, ans = 0;
	static int[] p;

	public static int findSet(int n) {
		if (p[n] == n)
			return n;
		return p[n] = findSet(p[n]);
	}

	public static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return;
		p[bRoot] = aRoot;
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/Jungol_1863_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		p = new int[n + 1];
		boolean[] check = new boolean[n + 1];
		for (int i = 0; i <= n; i++) {
			p[i] = i;
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		for (int i = 1; i <= n; i++) {
			int Root = findSet(i);
			if (!check[Root]) {
				check[Root] = true;
				ans++;
			}
		}
		System.out.println(ans);
	}
}
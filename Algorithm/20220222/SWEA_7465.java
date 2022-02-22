package hw_20220222;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7465 {

	static int ans;
	static int[] p;

	public static void makeSet(int n) {
		p = new int[n+1];
		for (int i = 0; i <= n; i++) {
			p[i] = i;
		}
	}

	public static int findSet(int n) {
		if (n == p[n]) return n;
		return p[n] = findSet(p[n]);
	}

	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return false;
		p[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_7465_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			ans = 0;
			boolean[] check = new boolean[N+1];
			int M = Integer.parseInt(st.nextToken());
			makeSet(N);
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}
			for (int i = 1; i <= N; i++) {
				if (!check[findSet(i)]) {
					check[findSet(i)] = true;
					ans++;
				}
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
}
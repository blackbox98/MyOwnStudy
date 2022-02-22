package hw_20220222;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3289 {

	static int n, m;
	static int[] p;

	public static int findSet(int n) {
		if (p[n] == n)
			return n;
		return p[n] = findSet(p[n]);
	}

	public static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return;
		p[bRoot] = aRoot;
	}
	
	public static boolean union(int op, int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return true;
		else return false;
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_3289_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			StringBuilder sb = new StringBuilder();
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			p = new int[n + 1];
			for (int i = 0; i <= n; i++) {
				p[i] = i;
			}
			sb.append("#" + tc + " ");
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int op = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (op == 0) {
					union(a, b);
				} else {
					if (union(op, a, b)) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				}
			}
			System.out.println(sb);
		}
	}
}
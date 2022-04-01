package edu.ssafy.chap09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5215 {

	static int T;
	static int N, L, ans;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_5215_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = Integer.MIN_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			arr = new int[N][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			int[] memo = new int[L + 1];
			for (int i = arr[0][1]; i < L; i++) {
				memo[i] = arr[0][0];
			}
			
			for (int i = 1; i < N; i++) { // 재료의 종류
				for (int j = L; j >= arr[i][1]; j--) {
					if (memo[j] <= memo[j - arr[i][1]] + arr[i][0]) {
						memo[j] = memo[j - arr[i][1]] + arr[i][0];
					}
				}
			}
			System.out.printf("#%d %d\n", tc, memo[L]);
		}
	}
	private static void powerSet(int[][] sel, int idx, boolean[] v) {
		if(idx == v.length) {
			int kSum = 0;
			int tSum = 0;
			for (int i = 0; i < v.length; i++) {
				if(v[i]) {
					tSum += sel[i][0];
					kSum += sel[i][1];
				}
			}
			if (kSum <= L) {
				ans = Math.max(ans, tSum);
			}
			return;
		}
		
		for (int i = 0; i < sel.length; i++) {
			if(!v[i]) {
				v[i] = true;
				powerSet(sel, idx+1, v);
				v[i] = false;
				powerSet(sel, idx+1, v);
			}
		}
	}
}
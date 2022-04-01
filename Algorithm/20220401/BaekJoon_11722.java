package edu.ssafy.chap09;
/*
6
10 30 10 20 20 10

// 3
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11722 {

	static int N, ans;
	static int[] A;
	static int[] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N + 1];
		memo = new int[1001];
		ans = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			int n = 0;
			for (int j = 1; j < i; j++) {
				if(A[j] > A[i]) {
					n = Math.max(n, memo[j]);
				}
			}
			
			memo[i] = n + 1;
			ans = Math.max(ans, memo[i]);
		}
		System.out.println(ans);
	}
}
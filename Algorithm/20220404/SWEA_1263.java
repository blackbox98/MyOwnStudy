package hw_20220404;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1263 {

	static int T, N, ans;
	static int[][] graph;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_1263_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			graph = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
					if(i != j && graph[i][j] == 0) {
						graph[i][j] = 987654321;
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(j == i) continue;
					for (int k = 0; k < N; k++) {
						if(k == i || k == j) continue;
						graph[j][k] = Math.min(graph[j][i] + graph[i][k], graph[j][k]);
					}
				}
			}
			for (int i = 0; i < N; i++) {
				int dist = 0;
				for (int j = 0; j < N; j++) {
					dist += graph[i][j];
				}
				ans = Math.min(ans, dist);
			}
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
}
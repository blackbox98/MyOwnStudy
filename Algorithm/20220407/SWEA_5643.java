package hw_20220407;

/*
1
6
6
1 5
3 4
5 4
4 2
4 6
5 2

// #1 1
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5643 {

	static int N, M, ans;
	static int[][] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			adjList = new int[N + 1][N + 1];
			StringTokenizer st = null;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				adjList[start][end] = 1;
			}

			ans = 0; // 자신의 키를 정확히 알 수 있는 학생의 수
			for (int i = 1; i <= N; i++) {
				if (gtBfs(i, new boolean[N + 1]) + ltBfs(i, new boolean[N + 1]) == N - 1) {
					ans++;
				}
			}

			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	static int gtBfs(int start, boolean[] v) {
		int cnt = 0;
		Queue<Integer> queue = new LinkedList<>();
		v[start] = true;
		queue.offer(start);
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int i = 1; i <= N; i++) { // 모든 학생을 들여다보며 자신보다 키가 큰 학생을 따라 탐색
				if (adjList[cur][i] != 0 && !v[i]) {
					v[i] = true;
					cnt++;
					queue.offer(i);
				}
			}
		}
		return cnt;
	}
	
	static int ltBfs(int start, boolean[] v) {
		int cnt = 0;
		Queue<Integer> queue = new LinkedList<>();
		v[start] = true;
		queue.offer(start);
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int i = 1; i <= N; i++) { // 모든 학생을 들여다보며 자신보다 키가 큰 학생을 따라 탐색
				if (adjList[i][cur] != 0 && !v[i]) {
					v[i] = true;
					++cnt;
					queue.offer(i);
				}
			}
		}
		return cnt;
	}
}
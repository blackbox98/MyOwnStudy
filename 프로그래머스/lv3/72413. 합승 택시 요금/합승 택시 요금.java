import java.util.Arrays;
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = Integer.MAX_VALUE;
		int[][] adj = new int[n + 1][n + 1];
		for (int i = 0; i < fares.length; i++) {
			int start = fares[i][0];
			int end = fares[i][1];
			int weight = fares[i][2];
			adj[start][end] = weight;
			adj[end][start] = weight;
		}
		for (int i = 1; i <= n; i++) {
			if (i == s) {
				answer = Math.min(answer, (dijk(adj, n, s, a) + dijk(adj, n, s, b)));
			} else if (i == a) {
				answer = Math.min(answer, (dijk(adj, n, s, a) + dijk(adj, n, a, b)));
			} else if (i == b) {
				answer = Math.min(answer, (dijk(adj, n, s, b) + dijk(adj, n, b, a)));
			} else {
				answer = Math.min(answer, (dijk(adj, n, s, i) + dijk(adj, n, i, a) + dijk(adj, n, i, b)));
			}
		}
		return answer;
	}

	private static int dijk(int[][] adj, int n, int s, int e) {
		boolean[] v = new boolean[n + 1];
		int[] dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		dist[s] = 0;
		for (int cnt = 0; cnt < n - 1; cnt++) {
			int minD = Integer.MAX_VALUE;
			int minIdx = -1;
			for (int i = 1; i <= n; i++) {
				if (!v[i] && dist[i] < minD) {
					minD = dist[i];
					minIdx = i;
				}
			}
			if (minIdx == -1) {
				break;
			}
			v[minIdx] = true;
			for (int i = 1; i <= n; i++) {
				if (!v[i] && adj[minIdx][i] != 0 && dist[i] > adj[minIdx][i] + dist[minIdx]) {
					dist[i] = adj[minIdx][i] + dist[minIdx];
				}
			}
		}
		return dist[e];
	}
}
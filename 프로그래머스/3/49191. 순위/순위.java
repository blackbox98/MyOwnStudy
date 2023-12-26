class Solution {
    public int solution(int n, int[][] results) {
        int[][] dist = new int[n + 1][n + 1];
        for (int[] result : results) {
            dist[result[0]][result[1]] = 1;
            dist[result[1]][result[0]] = -1;
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][j] == 0 && dist[i][k] != 0 && dist[i][k] == dist[k][j]) {
                        dist[i][j] = dist[i][k];
                        dist[j][i] = dist[i][j] * -1;
                    }
                }
            }
        }
        int answer = 0;
        L:
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == 0) cnt++;
                if (cnt > 1) continue L;
            }
            answer++;
        }
        return answer;
    }
}
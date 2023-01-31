class Solution {
    static boolean[][] visit;
    static int sizeOfArea;
    static int[] dr = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dc = {1, 0, -1, 0};
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        sizeOfArea = 0;
        visit = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j] && picture[i][j] != 0) {
                    numberOfArea++;
                    dfs(picture, i, j);
                    maxSizeOfOneArea = maxSizeOfOneArea >= sizeOfArea ? maxSizeOfOneArea : sizeOfArea;
                    sizeOfArea = 0;
                }
            }
        }
        return new int[]{numberOfArea, maxSizeOfOneArea};
    }
    
    static void dfs(int[][] picture, int r, int c) {
        if (visit[r][c]) return;

        visit[r][c] = true;
        sizeOfArea++;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr >= 0 && nr < picture.length && nc >= 0 && nc < picture[0].length && !visit[nr][nc] && picture[r][c] == picture[nr][nc]) {
                dfs(picture, nr, nc);
            }
        }
    }
}
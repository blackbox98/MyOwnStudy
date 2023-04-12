class Solution {
    static int[][] dir = {{1, 0}, {0, 1}, {-1, -1}}; // 하 우 좌상
    
    public int[] solution(int n) {
        int[][] triangle = new int[n][n];
        int total = 0;
        for (int i = 1; i <= n; i++) {
            total += i;
        }
        int sr = 0;
        int sc = 0;
        int cnt = 0;
        int turnCnt = 0;
        int turnIdx = 0;
        while (cnt < total && n > 0) {
            triangle[sr][sc] = ++cnt;
            turnCnt++;
            if (turnCnt == n) {
                turnCnt = 0;
                turnIdx = (turnIdx + 1) % 3;
                n--;
            }
            sr = sr + dir[turnIdx][0];
            sc = sc + dir[turnIdx][1];
        }
        int[] answer = new int[total];
        int idx = 0;
        for (int r = 0; r < triangle.length; r++) {
            for (int c = 0; c < triangle[r].length; c++) {
                if (triangle[r][c] > 0) answer[idx++] = triangle[r][c];
            }
        }
        return answer;
    }
}
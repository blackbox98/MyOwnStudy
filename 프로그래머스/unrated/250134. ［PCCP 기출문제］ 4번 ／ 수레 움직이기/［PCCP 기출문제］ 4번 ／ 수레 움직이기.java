class Solution {
    int R, C, answer;
    int[] redEnd, blueEnd;
    int[][] maze;
    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
    
    public int solution(int[][] maze) {
        this.maze = maze;
        R = maze.length;
        C = maze[0].length;
        int[] redStart = new int[2];
        int[] blueStart = new int[2];
        redEnd = new int[2];
        blueEnd = new int[2];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                switch (maze[r][c]) {
                    case 1:
                        redStart = new int[]{r, c};
                        break;
                    case 2:
                        blueStart = new int[]{r, c};
                        break;
                    case 3:
                        redEnd = new int[]{r, c};
                        break;
                    case 4:
                        blueEnd = new int[]{r, c};
                        break;
                    default:
                        break;
                }
            }
        }
        answer = Integer.MAX_VALUE;
        boolean[][] vRed = new boolean[R][C];
        boolean[][] vBlue = new boolean[R][C];
        vRed[redStart[0]][redStart[1]] = true;
        vBlue[blueStart[0]][blueStart[1]] = true;
        dfs(redStart, blueStart, vRed, vBlue, 0);
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    public void dfs(int[] curRed, int[] curBlue, boolean[][] vRed, boolean[][] vBlue, int turn) {
        if (answer <= turn) return;
        if (isCorrect(curRed, redEnd) && isCorrect(curBlue, blueEnd)) {
            answer = turn;
            return;
        }
        for (int[] d1 : dir) {
            int redR = curRed[0] + d1[0];
            int redC = curRed[1] + d1[1];
            int[] nextRed = new int[]{redR, redC};
            if (isCorrect(curRed, redEnd)) nextRed = curRed;
            if (check(nextRed[0], nextRed[1]) || maze[nextRed[0]][nextRed[1]] == 5 || (!isCorrect(curRed, redEnd) && vRed[nextRed[0]][nextRed[1]]))
                continue;
            for (int[] d2 : dir) {
                int blueR = curBlue[0] + d2[0];
                int blueC = curBlue[1] + d2[1];
                int[] nextBlue = new int[]{blueR, blueC};
                if (isCorrect(curBlue, blueEnd)) nextBlue = curBlue;
                if (check(nextBlue[0], nextBlue[1]) || maze[nextBlue[0]][nextBlue[1]] == 5 || (!isCorrect(curBlue, blueEnd) && vBlue[blueR][blueC]) || isCorrect(nextRed, nextBlue) || (!isCorrect(curRed, redEnd) && !isCorrect(curBlue, blueEnd) && (isCorrect(nextRed, curBlue)) && isCorrect(curRed, nextBlue)))
                    continue;
                vRed[nextRed[0]][nextRed[1]] = true;
                vBlue[nextBlue[0]][nextBlue[1]] = true;
                dfs(nextRed, nextBlue, vRed, vBlue, turn + 1);
                vRed[nextRed[0]][nextRed[1]] = false;
                vBlue[nextBlue[0]][nextBlue[1]] = false;
            }
        }
    }
    
    public boolean isCorrect(int[] start, int[] end) {
        return start[0] == end[0] && start[1] == end[1];
    }
    
    public boolean check(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }
}
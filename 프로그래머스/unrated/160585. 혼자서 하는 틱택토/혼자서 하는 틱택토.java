class Solution {
    public int solution(String[] board) {
        int answer = 1;
        int R = board.length;
        int C = board[0].length();
        int oNum = 0;
        int xNum = 0;
        int oWin = 0;
        int xWin = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                char ch = board[r].charAt(c);
                if (ch == 'O') oNum++;
                if (ch == 'X') xNum++;
            }
        }
        for (int i = 0; i < R; i++) {
            if (board[i].charAt(0) != '.' && board[i].charAt(0) == board[i].charAt(1) && board[i].charAt(1) == board[i].charAt(2)) {
                if (board[i].charAt(0) == 'O') oWin++;
                else xWin++;
            }
            if (board[0].charAt(i) != '.' && board[0].charAt(i) == board[1].charAt(i) && board[1].charAt(i) == board[2].charAt(i)) {
                if (board[0].charAt(i) == 'O') oWin++;
                else xWin++;
            }
        }
        char ch = board[1].charAt(1);
        if (ch != '.') {
            if (board[0].charAt(0) == ch && board[2].charAt(2) == ch) {
                if (ch == 'O') oWin++;
                else xWin++;
            }
            if (board[0].charAt(2) == ch && board[2].charAt(0) == ch) {
                if (ch == 'O') oWin++;
                else xWin++;
            }
        }
        if (oWin > 0 && xWin > 0) answer = 0;
        else if (oNum < xNum || oNum - xNum > 1 || (oWin > 0 && oNum <= xNum) || (xWin > 0 && oNum > xNum)) answer = 0;
        return answer;
    }
}
class Solution
{
    public int solution(int [][]board)
    {
        int answer = board[0][0] == 1 ? 1 : 0;
        for (int r = 1; r < board.length; r++) {
            for (int c = 1; c < board[r].length; c++) {
                if (board[r][c] == 1) {
                    board[r][c] = Math.min(Math.min(board[r - 1][c], board[r][c - 1]), board[r - 1][c - 1]) + 1;
                    answer = Math.max(answer, board[r][c]);
                }
            }
        }
        return answer * answer;
    }
}
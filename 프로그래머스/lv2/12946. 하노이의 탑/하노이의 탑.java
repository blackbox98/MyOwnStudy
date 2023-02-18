import java.util.ArrayList;
import java.util.List;

class Solution {
    static List<Move> moveList;
    
    public int[][] solution(int n) {
        moveList = new ArrayList<>();
        hanoi(n,1, 2, 3);
        int[][] answer = new int[moveList.size()][2];
        for (int i = 0; i < answer.length; i++) {
            Move m = moveList.get(i);
            answer[i][0] = m.from;
            answer[i][1] = m.to;
        }
        return answer;
    }
    
    private static void hanoi(int n, int from, int middle, int to) {
        if (n == 0) return;
        hanoi(n - 1, from, to, middle);
        moveList.add(new Move(from, to));
        hanoi(n - 1, middle, from, to);
    }
    
    private static class Move {
        int from;
        int to;

        public Move(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}
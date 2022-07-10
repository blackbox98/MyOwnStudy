import java.util.ArrayList;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        ArrayList<Integer> list = new ArrayList<>();
        int N = board[0].length;
        for (int i = 0; i < moves.length; i++) {
			for (int j = 0; j < N; j++) {
				int target = board[j][moves[i] - 1];
				if(target != 0) {
					if (list.isEmpty()) {
						list.add(target);
						board[j][moves[i] - 1] = 0;
						break;
					}
					if (list.get(list.size() - 1) != target) {
						list.add(target);
					} else {
						list.remove(list.size() - 1);
						answer+=2;
					}
					board[j][moves[i] - 1] = 0;
					break;
				}
			}
		}
        return answer;
    }
}
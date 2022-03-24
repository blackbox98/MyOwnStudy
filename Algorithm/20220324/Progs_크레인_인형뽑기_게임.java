package programmers;

import java.util.ArrayList;

public class Progs_크레인_인형뽑기_게임 {

	public static void main(String[] args) {
		int[][] board = { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 3 }, { 0, 2, 5, 0, 1 }, { 4, 2, 4, 4, 2 }, { 3, 5, 1, 3, 1 } };
		int[] moves = { 1, 5, 3, 5, 1, 2, 1, 4 };
		System.out.println(solution(board, moves));
	}
	
	public static int solution(int[][] board, int[] moves) {
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
import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        int[] no1 = {1, 2, 3, 4, 5};
        int[] no2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] no3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] score = new int[3];
        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < answers.length; j++) {
                if (answers[j] == no1[j % no1.length]) {
                    score[0]++;
                }
                if (answers[j] == no2[j % no2.length]) {
                    score[1]++;
                }
                if (answers[j] == no3[j % no3.length]) {
                    score[2]++;
                }
            }
        }
        int top = Math.max(score[0], Math.max(score[1], score[2]));
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < score.length; i++) {
            if (score[i] == top) answer.add(i + 1);
        }
        return answer.stream().mapToInt(n -> n).toArray();
    }
}
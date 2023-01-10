class Solution {
    public String solution(int[] food) {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < food.length; i++) {
            for (int j = 0; j < food[i] / 2; j++) {
                answer.append(i);
            }
        }
        String reverse = answer.reverse().toString();
        answer.reverse();
        answer.append(0).append(reverse);
        return answer.toString();
    }
}
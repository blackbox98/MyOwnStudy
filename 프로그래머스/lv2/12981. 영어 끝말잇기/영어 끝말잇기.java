import java.util.Stack;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer;
        Stack<String> stack = new Stack<>();
        int num = 0;
        int turn = 0;
        char lastAlpha = words[0].charAt(0);
        boolean goal = true;
        for (String word : words) {
            if (++num % n == 1) turn++;
            if (lastAlpha != word.charAt(0) || stack.contains(word)) {
                goal = false;
                break;
            }
            stack.push(word);
            lastAlpha = word.charAt(word.length() - 1);
        }
        if (goal) answer = new int[] {0, 0};
        else answer = new int[] {num % n == 0 ? n : num % n, turn};
        return answer;
    }
}
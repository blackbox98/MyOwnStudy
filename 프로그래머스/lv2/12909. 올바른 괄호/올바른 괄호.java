import java.util.Stack;
class Solution {
    boolean solution(String s) {
        if (s.charAt(0) == ')' || s.charAt(s.length() - 1) == '(') return false;
        boolean answer = true;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(c);
            else {
                if (stack.isEmpty()) {
                    answer = false;
                    break;
                }
                stack.pop();
            }
        }
        if (!stack.isEmpty()) answer = false;
        return answer;
    }
}
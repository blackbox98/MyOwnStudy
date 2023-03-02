import java.util.Stack;

class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder answer = new StringBuilder();
        Stack<Character> skipChar = new Stack<>();
        for (char c : skip.toCharArray()) {
            skipChar.push(c);
        }
        for (char c : s.toCharArray()) {
            int plus = 0;
            char target = ' ';
            int cnt = index;
            while (cnt > 0) {
                cnt--;
                plus = (plus + 1) % 26;
                target = (char) ((c + plus - 'a') % 26 + 'a');
                while (true) {
                    if (skipChar.contains(target)) {
                        plus = (plus + 1) % 26;
                        target = (char) ((c + plus - 'a') % 26 + 'a');
                    }
                    else break;
                }
            }
            answer.append(target);
        }
        return answer.toString();
    }
}
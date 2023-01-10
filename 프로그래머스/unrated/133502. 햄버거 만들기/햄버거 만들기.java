import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        for (int n : ingredient) {
            stack.push(n);
            if (stack.size() > 3
            && stack.get(stack.size() - 4) == 1
            && stack.get(stack.size() - 3) == 2
            && stack.get(stack.size() - 2) == 3
            && stack.get(stack.size() - 1) == 1) {
                for (int i = 0; i < 4; i++) {
                    stack.pop();
                }
                answer++;
            }
        }
        return answer;
    }
}
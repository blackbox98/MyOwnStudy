import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        int target = 1;
        while (answer < order.length) {
            int box = order[answer];
            if (box == target) {
                answer++;
                target++;
            } else {
                if (!stack.isEmpty()) {
                    if (stack.peek() == box) {
                        stack.pop();
                        answer++;
                        continue;
                    } else if (stack.peek() > box) break;
                }
                stack.push(target++);
            }
        }
        return answer;
    }
}
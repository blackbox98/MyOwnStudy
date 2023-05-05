import java.util.Stack;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numbers.length; i++) {
            int n = numbers[i];
            while (!stack.isEmpty() && numbers[stack.peek()] < n) {
                answer[stack.pop()] = n;
            }
            stack.push(i);
        }
        for (int idx : stack) answer[idx] = -1;
        return answer;
    }
}
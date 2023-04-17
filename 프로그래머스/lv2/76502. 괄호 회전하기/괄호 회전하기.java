import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Solution {
    static int size;
    static Queue<Character> queue;
    
    public int solution(String s) {
        int answer = 0;
        size = s.length();
        queue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            queue.offer(c);
        }
        for (int i = 0; i < size; i++) {
            if (check()) answer++;
            char c = queue.poll();
            queue.offer(c);
        }
        return answer;
    }
    
    private static boolean check() {
        boolean flag = false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            char c = queue.poll();
            queue.offer(c);
            if (stack.isEmpty()) stack.push(c);
            else {
                switch (stack.peek()) {
                    case '(':
                        if (c == ')') stack.pop();
                        else stack.push(c);
                        break;
                    case '{':
                        if (c == '}') stack.pop();
                        else stack.push(c);
                        break;
                    case '[':
                        if (c == ']') stack.pop();
                        else stack.push(c);
                        break;
                }
            }
        }
        if (stack.isEmpty()) flag = true;
        return flag;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static String s;
    static int answer, calc;
    static char c;
    static Stack<Character> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        stack = new Stack<>();
        answer = 0;
        calc = 1;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
                calc *= 2;
            } else if (c == '[') {
                stack.push(c);
                calc *= 3;
            } else if (c == ')') {
                if (!check(i, 2, '(')) break;
            } else if (c == ']') {
                if (!check(i, 3, '[')) break;
            }
        }
        if (!stack.isEmpty()) answer = 0;
        System.out.println(answer);
    }

    private static boolean check(int i, int n, char target) {
        if (stack.isEmpty() || stack.peek() != target) {
            stack.push(c);
            return false;
        } else if (s.charAt(i - 1) == target) {
            answer += calc;
        }
        stack.pop();
        calc /= n;
        return true;
    }
}
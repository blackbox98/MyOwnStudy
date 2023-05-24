import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String bomb = br.readLine();
        int bombLen = bomb.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));
            if (stack.size() >= bombLen) {
                boolean flag = true;
                for (int j = 0; j < bombLen; j++) {
                    if (stack.get(stack.size() - bombLen + j) != bomb.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    for (int j = 0; j < bombLen; j++) {
                        stack.pop();
                    }
                }
            }
        }
        StringBuilder answer = new StringBuilder();
        if (!stack.isEmpty()) {
            for (char c : stack) {
                answer.append(c);
            }
        }
        else answer.append("FRULA");
        System.out.println(answer);
    }
}
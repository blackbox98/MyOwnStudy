import java.util.Stack;

class Solution {
    public String solution(String p) {
        String answer = change(p);
        return answer;
    }
    
    private static String change(String p) {
        if ("".equals(p)) return p;
        int state = 0;
        StringBuilder u = new StringBuilder();
        StringBuilder v = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == '(') state++;
            else state--;
            if (state == 0) {
                u.append(p.substring(0, i + 1));
                v.append(p.substring(i + 1));
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (checkCorrect(u.toString())) {
            sb.append(u);
            sb.append(change(v.toString()));
        } else {
            sb.append('(');
            sb.append(change(v.toString()));
            sb.append(')');
            u = new StringBuilder(u.substring(1, u.length() - 1));
            for (int i = 0; i < u.length(); i++) {
                if (u.charAt(i) == '(') sb.append(')');
                else sb.append('(');
            }
        }
        return sb.toString();
    }

    private static boolean checkCorrect(String s) {
        boolean pass = true;
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(') stack.add('(');
            else{
                if(stack.isEmpty() || stack.peek() == ')') {
                    pass = false;
                    break;
                }
                else stack.pop();
            }
        }
        return pass;
    }
}
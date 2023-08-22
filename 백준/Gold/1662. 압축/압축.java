import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static char[] arr;
    static int[] bracket;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        arr = S.toCharArray();
        bracket = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') stack.push(i);
            else if (arr[i] == ')') bracket[stack.pop()] = i;
        }
        System.out.println(recursive(0, arr.length));
    }

    private static int recursive(int start, int end) {
        int cnt = 0;
        for (int i = start; i < end; i++) {
            if (arr[i] == '(') {
                cnt += (arr[i - 1] - '0') * recursive(i + 1, bracket[i]) - 1;
                i = bracket[i];
            } else cnt++;
        }
        return cnt;
    }
}
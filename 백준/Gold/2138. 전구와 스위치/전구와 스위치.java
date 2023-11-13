import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static int N, answer, cnt;
    static String goal;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder notPressZero = new StringBuilder(br.readLine());
        StringBuilder pressZero = new StringBuilder(notPressZero.toString());
        goal = br.readLine();
        answer = Integer.MAX_VALUE;
        cnt = 0;
        findSwitch(notPressZero);
        pressZero = press(pressZero, 0);
        cnt = 1;
        findSwitch(pressZero);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
    
    private static void findSwitch(StringBuilder cur) {
        for (int i = 1; i < N; i++) {
            if (cur.charAt(i - 1) == goal.charAt(i - 1)) continue;
            cur = press(cur, i);
            cnt++;
        }
        if (goal.equals(cur.toString())) answer = Math.min(answer, cnt); 
    }
    
    private static StringBuilder press(StringBuilder cur, int idx) {
        if (idx >= 1) cur.setCharAt(idx - 1, (char) ('1' + '0' - cur.charAt(idx - 1)));
        cur.setCharAt(idx, (char) ('1' + '0' - cur.charAt(idx)));
        if (idx < N - 1) cur.setCharAt(idx + 1, (char) ('1' + '0' - cur.charAt(idx + 1)));
        return cur;
    }
}
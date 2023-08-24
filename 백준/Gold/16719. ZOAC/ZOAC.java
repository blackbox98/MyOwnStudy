import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static String S;
    static boolean[] v;
    static StringBuilder answer;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        v = new boolean[S.length()];
        answer = new StringBuilder();
        ZOAC(0, S.length());
        System.out.println(answer);
    }
    
    private static void ZOAC(int start, int end) {
        if (start >= end) return;
        int minIdx = start;
        for (int i = start; i < end; i++) {
            if (S.charAt(minIdx) > S.charAt(i)) minIdx = i;
        }
        v[minIdx] = true;
        for (int i = 0; i < S.length(); i++) {
            if (v[i]) answer.append(S.charAt(i));
        }
        answer.append("\n");
        ZOAC(minIdx + 1, end);
        ZOAC(start, minIdx);
    }
}
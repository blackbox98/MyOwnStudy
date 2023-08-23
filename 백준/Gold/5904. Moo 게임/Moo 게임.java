import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static char answer = ' ';
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      playMoo(N);
      System.out.println(answer);
    }
    
    private static void playMoo(int N) {
        int len = 3;
        int idx = 0;
        if (N == 1) answer = 'm';
        else if (N <= 3) answer = 'o';
        else {
            while (len < N) {
                len = len * 2 + idx + 4;
                idx++;
            }
            int lastLen = (len - idx - 3) / 2;
            if (len - lastLen + 1 <= N) playMoo(N - len + lastLen);
            else if (N == lastLen + 1) answer = 'm';
            else answer = 'o';
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int start = 100;
        int N = Integer.parseInt(br.readLine());
        if (start == N) {
            System.out.println(0);
            return;
        }
        boolean[] broken = new boolean[10];
        int M = Integer.parseInt(br.readLine());
        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }
        int answer = Math.abs(N - start);
        L:
        for (int i = 0; i <= 999999; i++) {
            String s = String.valueOf(i);
            for (int j = 0; j < s.length(); j++) {
                if (broken[s.charAt(j) - '0']) continue L;
            }
            answer = Math.min(answer, Math.abs(i - N) + s.length());
        }
        System.out.println(answer);
    }
}
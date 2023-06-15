import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringBuilder answer = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        boolean braket = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '<' || c == '>' || c == ' ') {
                if (c != ' ') braket = !braket;
                answer.append(sb.reverse()).append(c);
                sb = new StringBuilder();
                continue;
            }
            if (!braket) {
                sb.append(c);
            } else answer.append(c);
        }
        if (sb.length() > 0) {
            answer.append(sb.reverse());
        }
        System.out.println(answer);
    }
}
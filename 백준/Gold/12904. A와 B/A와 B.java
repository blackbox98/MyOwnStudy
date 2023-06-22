import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder S = new StringBuilder(br.readLine());
        StringBuilder T = new StringBuilder(br.readLine());
        int idx = T.length() - 1;
        while (S.length() < T.length()) {
            if (T.charAt(idx) == 'A') {
                T.deleteCharAt(idx);
                if (idx > 0) idx--;
            }
            else {
                T.deleteCharAt(idx);
                if (idx == 0) idx = T.length() - 1;
                else idx = 0;
            }
        }
        if (idx == 0) T.reverse();
        System.out.println(S.toString().equals(T.toString()) ? 1 : 0);
    }
}
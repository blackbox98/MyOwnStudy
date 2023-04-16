import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int size = 5;
        int cnt = 0;
        String[] str = new String[size];
        for (int i = 0; i < size; i++) {
            str[i] = br.readLine();
            cnt += str[i].length();
        }
        while (cnt > 0) {
            for (int i = 0; i < size; i++) {
                if (str[i].length() == 0) continue;
                answer.append(str[i].charAt(0));
                str[i] = str[i].substring(1);
                cnt--;
            }
        }
        System.out.println(answer.toString());
    }
}

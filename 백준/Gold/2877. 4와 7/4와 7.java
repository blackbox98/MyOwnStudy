import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        while (K > 0) {
            if (K % 2 == 1) answer.append("4");
            else {
                answer.append("7");
                K--;
            }
            K /= 2;
        }
        answer.reverse();
        System.out.println(answer);
    }
}
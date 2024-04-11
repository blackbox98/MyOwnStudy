import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = N / 5;
        N %= 5;
        if (answer == 0 && N % 2 == 1) answer = -1;
        else if (N % 2 == 0) answer += N / 2;
        else answer += N / 2 + 2;
        System.out.println(answer);
    }
}
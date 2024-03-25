import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N <= 3) {
            System.out.println(N);
            return;
        }
        long[] memo = new long[N + 1];
        memo[1] = 1;
        memo[2] = 2;
        memo[3] = 3;
        for (int i = 4; i <= N; i++) {
            memo[i] = (memo[i - 1] + memo[i - 2]) % 15746;
        }
        System.out.println(memo[N]);
    }
}
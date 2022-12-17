import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(solve(N));
    }

    public static int solve(int n) {
        int cnt = 0;

        if (n < 100) {
            return n;
        } else {
            cnt = 99;
            for (int i = 100; i <= n; i++) {
                int hundreds = i / 100; // 백의 자리
                int tens = (i / 10) % 10; // 십의 자리
                int units = i % 10; // 일의 자리

                if ((hundreds - tens) == (tens - units)) { // 각 자리가 등차수열을 이룬 경우
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
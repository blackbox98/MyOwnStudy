import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static List<Long> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long answer;
        if (N <= 10) answer = N;
        else if (N >= 1023) answer = -1;
        else {
            list = new ArrayList<>();
            for (int n = 0; n <= 9; n++) {
                descNum(n, 1);
            }
            Collections.sort(list);
            answer = list.get(N);
        }
        System.out.println(answer);
    }

    private static void descNum(long n, int digit) {
        if (digit > 10) return;
        list.add(n);
        for (long i = n % 10 - 1; i >= 0; i--) {
            descNum((n * 10) + i, digit + 1);
        }
    }
}
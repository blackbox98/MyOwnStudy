import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        if (N == 1) {
            System.out.println(answer);
            return;
        }
        boolean[] notPrimeNum = new boolean[N + 1];
        notPrimeNum[0] = true;
        notPrimeNum[1] = true;
        for (int i = 2; i * i <= N; i++) {
            if (!notPrimeNum[i]) {
                for (int j = i * i; j <= N; j += i) {
                    notPrimeNum[j] = true;
                }
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (!notPrimeNum[i]) list.add(i);
        }
        int sum = 0;
        int left = 0;
        int right = 0;
        int size = list.size();
        while (left <= right) {
            if (sum >= N) sum -= list.get(left++);
            else if (right == size) break;
            else sum += list.get(right++);
            if (sum == N) answer++;
        }
        System.out.println(answer);
    }
}
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] values = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(values);
        long answer = 0, sum;
        int base, left, right, lCnt, rCnt;
        for (int i = 0; i < N; i++) {
            if (values[i] > 0) break;
            base = values[i];
            left = i + 1;
            right = N - 1;
            while (left < right) {
                sum = base + values[left] + values[right];
                if (sum == 0) {
                    if (values[left] == values[right]) {
                        answer += combination(right - left + 1);
                        break;
                    }
                    lCnt = 1;
                    rCnt = 1;
                    while (values[left] == values[left + 1]) {
                        left++;
                        lCnt++;
                    }
                    while (values[right] == values[right - 1]) {
                        right--;
                        rCnt++;
                    }
                    answer += (long) lCnt * rCnt;
                }
                if (sum > 0) right--;
                else left++;
            }
        }
        System.out.println(answer);
    }

    private static long combination(int n) {
        return (long) n * (n - 1) / 2;
    }
}
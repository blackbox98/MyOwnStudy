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
        int[] answer = new int[2];
        int gap = Integer.MAX_VALUE;
        int left = 0;
        int right = N - 1;
        while (left < right) {
            int sum = values[left] + values[right];
            if (gap > Math.abs(sum)) {
                answer[0] = values[left];
                answer[1] = values[right];
                gap = Math.abs(sum);
            }
            if (sum < 0) left++;
            else right--;
        }
        System.out.printf("%d %d\n", answer[0], answer[1]);
    }
}
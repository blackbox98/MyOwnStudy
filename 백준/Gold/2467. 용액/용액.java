import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
        int[] answer = new int[2];
        if (values[0] < 0 && values[N - 1] < 0) {
            answer[0] = values[N - 2];
            answer[1] = values[N - 1];
        } else if (values[0] > 0 && values[N - 1] > 0) {
            answer[0] = values[0];
            answer[1] = values[1];
        } else {
            int left = 0;
            int right = N - 1;
            int gap = Integer.MAX_VALUE;
            while (left < right) {
                int sum = values[left] + values[right];
                if (gap >= Math.abs(sum)) {
                    gap = Math.abs(sum);
                    answer[0] = values[left];
                    answer[1] = values[right];
                }
                if (sum < 0) left++;
                else right--;
            }
        }
        System.out.printf("%d %d\n", answer[0], answer[1]);
    }
}
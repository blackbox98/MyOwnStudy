import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        int[] sumToRight = new int[N];
        int[] sumToLeft = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        nums[0] = Integer.parseInt(st.nextToken());
        sumToRight[0] = nums[0];
        int answer = nums[0];
        for (int i = 1; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            sumToRight[i] = Math.max(sumToRight[i - 1] + nums[i], nums[i]);
            answer = Math.max(answer, sumToRight[i]);
        }
        sumToLeft[N - 1] = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            sumToLeft[i] = Math.max(sumToLeft[i + 1] + nums[i], nums[i]);
        }
        for (int i = 1; i < N - 1; i++) {
            answer = Math.max(answer, sumToRight[i - 1] + sumToLeft[i + 1]);
        }
        System.out.println(answer);
    }
}
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(nums);
        long answer = 0;
        int idx = 0;
        if (nums[0] < 0) {
            for (int i = 0; i < N; i++) {
                if (nums[i] >= 0) {
                    idx = i;
                    break;
                }
                if (i + 1 < N) {
                    if (nums[i + 1] > 0) {
                        answer += nums[i];
                        idx = i + 1;
                        break;
                    }
                    answer += (long) nums[i] * nums[i + 1];
                    i++;
                } else answer += nums[i];
            }
            if (idx == 0) idx = N;
        }
        for (int i = N - 1; i >= idx; i--) {
            if (i - 1 >= idx) {
                int sum = nums[i] + nums[i - 1];
                int mul = nums[i] * nums[i - 1];
                if (sum < mul) {
                    answer += mul;
                    i--;
                    continue;
                }
            }
            answer += nums[i];
        }
        System.out.println(answer);
    }
}
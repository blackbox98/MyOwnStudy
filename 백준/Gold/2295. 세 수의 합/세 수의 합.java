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
        int size = N * (N + 1) / 2;
        int[] sumXY = new int[size];
        int idx = 0;
        for (int x = 0; x < N; x++) {
            for (int y = x; y < N; y++) {
                sumXY[idx++] = nums[x] + nums[y];
            }
        }
        Arrays.sort(sumXY);
        int answer = 0;
        int result, left, right, mid, target;
        L:
        for (int k = N - 1; k >= 0; k--) {
            for (int z = k; z >= 0; z--) {
                result = nums[k] - nums[z];
                left = 0;
                right = size;
                while (left < right) {
                    mid = (left + right) / 2;
                    target = sumXY[mid];
                    if (target < result) left = mid + 1;
                    else if (target > result) right = mid;
                    else {
                        answer = nums[k];
                        break L;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
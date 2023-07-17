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
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        int answer = 0;
        int idx = 0;
        int target, left, right, sum;
        while (N > 2 && idx < N) {
            target = nums[idx];
            left = 0;
            right = N - 1;
            while (left < right) {
                sum = nums[left] + nums[right];
                if (target < sum) right--;
                else if (target > sum) left++;
                else {
                    if (left == idx) left++;
                    else if (right == idx) right--;
                    else {
                        answer++;
                        break;
                    }
                }
            }
            idx++;
        }
        System.out.println(answer);
    }
}
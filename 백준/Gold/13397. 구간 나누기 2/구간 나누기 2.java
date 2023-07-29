import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        nums = new int[N];
        int right = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, nums[i]);
        }
        int left = 0;
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (division(mid) > M) left = mid + 1;
            else right = mid;
        }
        System.out.println(left);
    }

    private static int division(int mid) {
        int cnt = 1;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            if (max - min > mid) {
                cnt++;
                min = num;
                max = num;
            }
        }
        return cnt;
    }
}
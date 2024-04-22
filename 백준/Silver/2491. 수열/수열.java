import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        int[] LIS = new int[N];
        int[] LDS = new int[N];
        int answer = 1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        nums[0] = Integer.parseInt(st.nextToken());
        LIS[0] = 1;
        LDS[0] = 1;
        for (int i = 1; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            LIS[i] = 1;
            LDS[i] = 1;
            if (nums[i] >= nums[i - 1]) LIS[i] = LIS[i - 1] + 1;
            if (nums[i] <= nums[i - 1]) LDS[i] = LDS[i - 1] + 1;
            answer = Math.max(answer, Math.max(LIS[i], LDS[i]));
        }
        System.out.println(answer);
    }
}
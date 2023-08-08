import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] nums = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int answer = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int sum = 0;
        while (left <= right && right <= N) {
            if (sum >= S) {
                answer = Math.min(answer, right - left);
                sum -= nums[left++];
            } else sum += nums[right++];
        }
        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}
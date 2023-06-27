import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
        String answer;
        if (N == 1 || (N == 2 && nums[0] != nums[1])) answer = "A";
        else if (N == 2) answer = "" + nums[0];
        else {
            int a, b;
            if (nums[0] == nums[1]) {
                a = 1;
                b = 0;
            } else {
                a = (nums[2] - nums[1]) / (nums[1] - nums[0]);
                b = nums[1] - (nums[0] * a);
            }
            boolean flag = true;
            for (int i = 2; i < N; i++) {
                if (nums[i - 1] * a + b != nums[i]) {
                    flag = false;
                    break;
                }
            }
            if (flag) answer = "" + (nums[N - 1] * a + b);
            else answer = "B";
        }
        System.out.println(answer);
    }
}
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                String empty = br.readLine();
                if (p.contains("D")) System.out.println("error");
                else System.out.println(empty);
                continue;
            }
            StringBuilder answer = new StringBuilder();
            String s = br.readLine();
            String[] nums = s.substring(1, s.length() - 1).split(",");
            boolean reverse = false;
            int start = 0;
            int end = nums.length - 1;
            int cnt = nums.length;
            for (char function : p.toCharArray()) {
                if (function == 'R') {
                    reverse = !reverse;
                } else {
                    if (cnt == 0) {
                        answer = new StringBuilder("error");
                        break;
                    }
                    if (reverse) end--;
                    else start++;
                    cnt--;
                }
            }
            if (answer.length() == 0) {
                if (cnt == 0) answer.append("[]");
                else {
                    answer.append('[');
                    if (reverse) {
                        for (int i = end; i >= start; i--) {
                            answer.append(nums[i]).append(',');
                        }
                    }
                    else {
                        for (int i = start; i <= end; i++) {
                            answer.append(nums[i]).append(',');
                        }
                    }
                    answer.deleteCharAt(answer.length() - 1);
                    answer.append(']');
                }
            }
            System.out.println(answer);
        }
    }
}
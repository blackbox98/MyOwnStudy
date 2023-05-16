import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] nums = new int[N];
            int[] values = new int[10];
            Queue<Integer> queue = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
                queue.offer(nums[i]);
                values[nums[i]]++;
            }
            int top = 0;
            for (int i = values.length - 1; i >= 0; i--) {
                if (values[i] > 0) {
                    top = i;
                    break;
                }
            }
            int answer = 0;
            while (!queue.isEmpty()) {
                int n = queue.poll();
                if (n == top){
                    answer++;
                    if (M == 0) break;
                    values[n]--;
                    if (values[n] == 0) {
                        for (int i = values.length - 1; i >= 0; i--) {
                            if (values[i] > 0) {
                                top = i;
                                break;
                            }
                        }
                    }
                } else queue.offer(n);
                M -= 1;
                if (M < 0) M = queue.size() - 1;
            }
            System.out.println(answer);
        }
    }
}

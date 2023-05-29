import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int answer = 0;
        int left = 0;
        int right = 0;
        while (left < N && right < N) {
            if (map.getOrDefault(nums[right], 0) < K) {
                map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
                right++;
            } else {
                answer = Math.max(answer, right - left);
                map.put(nums[left], map.get(nums[left]) - 1);
                left++;
            }
        }
        answer = Math.max(answer, right - left);
        System.out.println(answer);
    }
}
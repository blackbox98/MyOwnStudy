import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        String s;
        int N;
        int[] nums;
        StringTokenizer st;
        List<Integer> list;
        int target;
        int left, right, mid;
        while ((s = br.readLine()) != null) {
            N = Integer.parseInt(s.trim());
            nums = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            list = new ArrayList<>();
            list.add(nums[0]);
            for (int i = 1; i < N; i++) {
                target = nums[i];
                if (list.get(list.size() - 1) < target) list.add(target);
                else {
                    if (list.get(list.size() - 1) == target) continue;
                    left = 0;
                    right = list.size() - 1;
                    while (left < right) {
                        mid = (left + right) / 2;
                        if (list.get(mid) > target) right = mid;
                        else if (list.get(mid) < target) left = mid + 1;
                        else break;
                    }
                    list.set(left, target);
                }
            }
            answer.append(list.size()).append("\n");
        }
        System.out.println(answer);
    }
}
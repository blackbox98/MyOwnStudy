import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Solution {
    static char[] nums;
    static Set<Integer> set;
    static boolean[] v;
    
    public int solution(String numbers) {
        int answer = 0;
        nums = numbers.toCharArray();
        set = new HashSet<>();
        v = new boolean[nums.length];
        permutation(new ArrayList<>(), 0);
        for (int n : set) {
            int cnt = 0;
            for (int i = 1; i * i <= n; i++) {
                if (i * i == n) cnt++;
                else if (n % i == 0) cnt += 2;
            }
            if (cnt == 2) answer++;
        }
        return answer;
    }
    
    private static void permutation(ArrayList<Character> list, int cnt) {
        if (cnt == nums.length) {
            StringBuilder sb = new StringBuilder();
            for (char c : list) {
                sb.append(c);
            }
            if (sb.length() > 0) {
                int n = Integer.parseInt(sb.toString());
                if (n > 1) set.add(n);
            }
            return;
        }

        for (int i = 0; i < v.length; i++) {
            if (!v[i]) {
                v[i] = true;
                list.add(nums[i]);
                permutation(list, cnt + 1);
                v[i] = false;
                list.remove(list.size() - 1);
                permutation(list, cnt + 1);
            }
        }
    }
}
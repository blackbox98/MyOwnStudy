import java.util.HashSet;
import java.util.Set;
class Solution {
    public int solution(int[] nums) {
        Set<Integer> answer = new HashSet<>();
        for (int n : nums) {
            answer.add(n);
        }
        return nums.length / 2 > answer.size() ? answer.size() : nums.length / 2;
    }
}
import java.util.Arrays;
class Solution {
    public String solution(String s) {
        String[] str = s.split(" ");
        int[] nums = new int[str.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(str[i]);
        }
        Arrays.sort(nums);
        return nums[0] + " " + nums[nums.length - 1];
    }
}
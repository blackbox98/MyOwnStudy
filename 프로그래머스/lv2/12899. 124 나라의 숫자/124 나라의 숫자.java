class Solution {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        int[] nums = {4, 1, 2};
        while (n > 3) {
            int portion = n / 3;
            int remainder = n % 3;
            if (remainder == 0) portion--;
            answer.append(nums[remainder]);
            n = portion;
        }
        if (n < 4) answer.append(nums[n % 3]);
        return answer.reverse().toString();
    }
}
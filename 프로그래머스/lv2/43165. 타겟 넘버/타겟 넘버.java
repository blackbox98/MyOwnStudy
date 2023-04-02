class Solution {
    static int answer;
    static int TG;
    static int[] nums;
    static boolean[] v;
    
    public int solution(int[] numbers, int target) {
        answer = 0;
        TG = target;
        nums = numbers.clone();
        v = new boolean[numbers.length];
        recursive(0);
        return answer;
    }
    
    private static void recursive(int idx) {
        if (idx == v.length) {
            int sum = 0;
            for (int i = 0; i < v.length; i++) {
                if (v[i]) sum += nums[i] * -1;
                else sum += nums[i];
            }
            if (sum == TG) answer++;
            return;
        }

        v[idx] = true;
        recursive(idx + 1);
        v[idx] = false;
        recursive(idx + 1);
    }
}
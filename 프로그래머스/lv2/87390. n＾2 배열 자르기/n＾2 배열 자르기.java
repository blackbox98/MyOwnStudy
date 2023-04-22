class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int) (right - left + 1)];
        int idx = 0;
        for (long num = left; num <= right; num++) {
            int r = (int) (num / n);
            int c = (int) (num % n);
            answer[idx++] = Math.max(r, c) + 1;
        }
        return answer;
    }
}
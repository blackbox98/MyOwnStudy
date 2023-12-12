class Solution {
    public int[] solution(int n, int s) {
        int[] answer;
        if (s < n) {
            answer = new int[]{-1};
        } else {
            answer = new int[n];
            int quotient = s / n;
            int remainder = s % n;
            for (int i = 0; i < n; i++) {
                answer[i] = quotient;
                if (i >= n - remainder) answer[i]++;
            }
        }
        return answer;
    }
}
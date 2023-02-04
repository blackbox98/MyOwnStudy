class Solution {
    public int solution(int n) {
        int[] answer = new int[n + 1];
        answer[1] = 1;
        answer[2] = 2;
        for (int i = 3; i <= n; i++) {
            answer[i] = (answer[i - 2] + answer[i - 1]) % 1000000007;
        }
        return answer[n];
    }
}
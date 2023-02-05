class Solution {
    public int solution(int n) {
        if (n % 2 == 1) return 0;
        long[] answer = new long[n + 1];
        answer[0] = 1;
        answer[2] = 3;
        long sum = 0;
        for (int i = 4; i <= n; i+=2) {
            sum += (2 * answer[i - 4]) % 1000000007;
            answer[i] = ((3 * answer[i - 2]) + sum) % 1000000007;
        }
        return (int) answer[n];
    }
}
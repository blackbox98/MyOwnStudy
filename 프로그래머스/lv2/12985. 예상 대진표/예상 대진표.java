class Solution {
    public int solution(int n, int a, int b) {
        int answer = 0;
        while (n > 1) {
            a = (a + 1) / 2;
            b = (b + 1) / 2;
            answer++;
            if (a == b) break;
            n /= 2;
        }
        return answer;
    }
}
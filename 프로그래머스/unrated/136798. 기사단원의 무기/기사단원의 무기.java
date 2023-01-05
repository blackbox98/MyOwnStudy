class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        for (int i = 1; i <= number; i++) {
            int n = (int) Math.sqrt(i);
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (i % j == 0) {
                    cnt++;
                    if (i / j != j) cnt++;
                }
            }
            if (cnt > limit) cnt = power;
            answer += cnt;
        }
        return answer;
    }
}
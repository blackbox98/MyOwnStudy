class Solution {
    public int solution(int n) {
        int answer = 0;
        L : for (int i = 2; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j * j <= i; j++) {
                if (j * j == i) continue L;
                else if (i % j == 0) cnt += 2;
                if (cnt > 2) continue L;
            }
            answer++;
        }
        return answer;
    }
}
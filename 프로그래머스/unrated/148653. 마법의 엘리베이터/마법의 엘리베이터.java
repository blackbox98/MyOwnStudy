class Solution {
    public int solution(int storey) {
        int answer = 0;
        int plus = 0;
        String num = String.valueOf(storey);
        for (int i = num.length() - 1; i >= 0; i--) {
            int n = num.charAt(i) - '0' + plus;
            plus = 0;
            if (n >= 6 || n == 5 && i - 1 >= 0 && (num.charAt(i - 1) - '0') >= 5) {
                answer += 10 - n;
                plus = 1;
            } else answer += n;
        }
        answer += plus;
        return answer;
    }
}
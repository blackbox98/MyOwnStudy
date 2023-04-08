class Solution {
    public long solution(int w, int h) {
        long answer = (long) w * h;
        long gcd = 1;
        int min = Math.min(w, h);
        for (int i = min; i >= 1; i--) {
            if (w % i == 0 && h % i == 0) {
                gcd = i;
                break;
            }
        }
        if (gcd == min) {
            answer -= Math.max(w, h);
        } else {
            answer -= ((w / gcd) + (h / gcd) - 1) * gcd;
        }
        return answer;
    }
}
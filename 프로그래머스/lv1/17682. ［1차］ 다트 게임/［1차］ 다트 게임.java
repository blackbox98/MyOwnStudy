class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        int[] score = new int[3];
        int idx = 0;
        for (int i = 0; i < dartResult.length(); i++) {
            int n;
            if (dartResult.charAt(i) - '0' > 10) {
                if (i - 2 >= 0 && dartResult.charAt(i - 2) == '1') n = 10;
                else n = Character.getNumericValue(dartResult.charAt(i - 1));
            }
            else continue;
            if (dartResult.charAt(i) == 'S') {
                score[idx++] = n;
            } else if (dartResult.charAt(i) == 'D') {
                score[idx++] = (int) Math.pow(n, 2);
            } else if (dartResult.charAt(i) == 'T') {
                score[idx++] = (int) Math.pow(n, 3);
            }
        }
        int cnt = 0;
        for (int i = dartResult.length() - 1; i >= 0; i--) {
            if (dartResult.charAt(i) == '*') {
                if (cnt == 4) {
                    score[0] *= 2;
                } else {
                    score[2 - cnt / 2] *= 2;
                    score[1 - cnt / 2] *= 2;
                }
            } else if (dartResult.charAt(i) == '#') {
                score[2 - cnt / 2] *= -1;
            } else {
                cnt++;
            }
        }
        for (int n : score) {
            answer += n;
        }
        return answer;
    }
}
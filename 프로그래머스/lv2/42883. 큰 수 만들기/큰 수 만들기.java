class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        char[] cArr = number.toCharArray();
        int len = cArr.length - k;
        int idx = 0;
        while (answer.length() < len && idx < cArr.length) {
            char max = '0';
            for (int i = idx; i < answer.length() + k + 1; i++) {
                if (max < cArr[i]) {
                    max = cArr[i];
                    idx = i + 1;
                }
            }
            answer.append(max);
        }
        return answer.toString();
    }
}
class Solution {
    public int solution(int[] cookie) {
        int N = cookie.length;
        int answer = 0;
        for (int m = 0; m < N - 1; m++) {
            int l = m;
            int r = m + 1;
            int lCookies = cookie[m];
            int rCookies = cookie[m + 1];
            while (true) {
                if (lCookies == rCookies && answer < lCookies) answer = lCookies;
                else if (lCookies <= rCookies && l > 0) lCookies += cookie[--l];
                else if (lCookies > rCookies && r < N - 1) rCookies += cookie[++r];
                else break;
            }
        }
        return answer;
    }
}
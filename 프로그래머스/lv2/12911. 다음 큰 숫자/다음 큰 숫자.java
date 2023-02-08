class Solution {
    public int solution(int n) {
        int nCnt = Integer.bitCount(n);
        int answer = n + 1;
        while (true) {
            if (Integer.bitCount(answer) == nCnt) break;
            answer++;
        }
        return answer;
    }
}
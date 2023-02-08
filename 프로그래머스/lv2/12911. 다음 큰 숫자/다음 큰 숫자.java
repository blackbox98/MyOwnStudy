class Solution {
    public int solution(int n) {
        int n_one = Integer.bitCount(n);
        int answer = n + 1;
        while (true) {
            int answer_one = Integer.bitCount(answer);
            if (n_one == answer_one) break;
            answer++;
        }
        return answer;
    }
}
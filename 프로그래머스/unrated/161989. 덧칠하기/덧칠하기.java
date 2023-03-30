class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int coverMax = 0;
        for (int num : section) {
            if (coverMax <= num) {
                coverMax = num + m;
                answer++;
            }
        }
        return answer;
    }
}
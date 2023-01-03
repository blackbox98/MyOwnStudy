class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        boolean[] alpha_check = new boolean[26];
        int[] alpha_idx = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 97;
            if (!alpha_check[index]) {
                alpha_check[index] = true;
                answer[i] = -1;
            } else {
                answer[i] = i - alpha_idx[index];
            }
            alpha_idx[index] = i;
        }
        return answer;
    }
}
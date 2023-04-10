class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        while (!"1".equals(s)) {
            answer[0]++;
            int len = s.length();
            s = s.replaceAll("0", "");
            answer[1] += len - s.length();
            s = Integer.toBinaryString(s.length());
        }
        return answer;
    }
}
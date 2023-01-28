class Solution {
    public String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append(" ");
            } else {
                int chNum = s.charAt(i) + n;
                if (s.charAt(i) >= 'a') {
                    sb.append((char) (chNum > 'z' ? chNum - 26 : chNum));
                } else {
                    sb.append((char) (chNum > 'Z' ? chNum - 26 : chNum));
                }
            }
        }
        return sb.toString();
    }
}
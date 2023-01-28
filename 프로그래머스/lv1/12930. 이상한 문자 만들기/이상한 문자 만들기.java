class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                for (int j = i; j < s.length(); j++) {
                    if (s.charAt(j) == ' ') {
                        i = j - 1;
                        break;
                    } else {
                        if ((j - i) % 2 == 0) sb.append(String.valueOf(s.charAt(j)).toUpperCase());
                        else sb.append(String.valueOf(s.charAt(j)).toLowerCase());
                        if (j == s.length() - 1) i = j;
                    }
                }
            } else sb.append(" ");
        }
        return sb.toString();
    }
}
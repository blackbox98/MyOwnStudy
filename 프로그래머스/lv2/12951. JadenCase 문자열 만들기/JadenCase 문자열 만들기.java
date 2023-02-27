class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        for (String str : s.toLowerCase().split(" ")) {
            if (str.length() > 0) answer.append(str.substring(0, 1).toUpperCase()).append(str.substring(1));
            answer.append(" ");
        }
        if (s.charAt(s.length() - 1) != ' ') answer.deleteCharAt(answer.length() - 1);
        return answer.toString();
    }
}
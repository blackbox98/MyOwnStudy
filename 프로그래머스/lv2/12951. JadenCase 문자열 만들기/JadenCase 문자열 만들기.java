class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        for (String str : s.toLowerCase().split(" ")) {
            if (str.length() == 0) {
                answer.append(' ');
                continue;
            } else if (str.charAt(0) - '0' > 9) {
                answer.append((char) (str.charAt(0) - 32));
                if (str.length() > 1) answer.append(str.substring(1));
            } else {
                answer.append(str);
            }
            answer.append(' ');
        }
        if (s.substring(s.length() - 1).equals(" ")) return answer.toString();
        return answer.deleteCharAt(answer.length() - 1).toString();
    }
}
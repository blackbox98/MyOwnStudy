class Solution {
    public boolean solution(String s) {
        boolean answer = true;
        int length = s.length();
        s = s.replaceAll("\\D", "");
        if (length - s.length() != 0) answer = false;
        else if (length != 4 && length != 6) answer = false;
        return answer;
    }
}
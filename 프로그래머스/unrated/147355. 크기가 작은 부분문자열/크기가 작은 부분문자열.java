class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        if (t.length() == p.length()) {
            if (Long.parseLong(t) <= Long.parseLong(p)) answer++;
        } else {
            for (int i = 0; i < t.length() - p.length() + 1; i++) {
                if (Long.parseLong(t.substring(i, i + p.length())) <= Long.parseLong(p)) answer++;
            }
        }
        return answer;
    }
}
class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder answer = new StringBuilder();
        for (char c : s.toCharArray()) {
            int cNum = c;
            int cnt = index;
            while (cnt > 0) {
                cnt--;
                cNum = (cNum + 1) > 'z' ? 'a' : (cNum + 1);
                while (true) {
                    if (skip.contains(String.valueOf((char) cNum))) {
                        cNum = (cNum + 1) > 'z' ? 'a' : cNum + 1;
                    }
                    else break;
                }
            }
            answer.append((char) cNum);
        }
        return answer.toString();
    }
}
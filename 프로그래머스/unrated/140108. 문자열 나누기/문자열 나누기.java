class Solution {
    public int solution(String s) {
        int answer = 0;
        char x = s.charAt(0);
        int xNum = 1;
        int others = 0;
        boolean flag = false;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == x) {
                xNum++;
                flag = false;
            } else {
                others++;
                if (xNum == others) {
                    answer++;
                    flag = true;
                    if (i < s.length() - 1) {
                        x = s.charAt(i + 1);
                        xNum = 0;
                        others = 0;
                    } else {
                        break;
                    }
                } else {
                    flag = false;
                }
            }
        }
        if (!flag) {
            answer++;
        }
        return answer;
    }
}
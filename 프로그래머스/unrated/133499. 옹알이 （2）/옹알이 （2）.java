class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] canSpeak = new String[]{"aya", "ye", "woo", "ma"};
        for (String s : babbling) {
            if (s.replaceAll("[aemowy]", "").equals("")) {
                int idx = -1;
                for (int i = 0; i < s.length(); i++) {
                    switch (s.charAt(i)) {
                        case 'a' :
                            if (idx != 0
                            && i + 2 < s.length()
                            && s.substring(i, i + 3).equals(canSpeak[0])) {
                                idx = 0;
                                s = s.substring(0, i) + s.substring(i + 3, s.length());
                                i--;
                            }
                            break;
                        case 'm' :
                            if (idx != 3
                            && i + 1 < s.length()
                            && s.substring(i, i + 2).equals(canSpeak[3])) {
                                idx = 3;
                                s = s.substring(0, i) + s.substring(i + 2, s.length());
                                i--;
                            }
                            break;
                        case 'w' :
                            if (idx != 2
                            && i + 2 < s.length()
                            && s.substring(i, i + 3).equals(canSpeak[2])) {
                                idx = 2;
                                s = s.substring(0, i) + s.substring(i + 3, s.length());
                                i--;
                            }
                            break;
                        case 'y' :
                            if (idx != 1
                            && i + 1 < s.length()
                            && s.substring(i, i + 2).equals(canSpeak[1])) {
                                idx = 1;
                                s = s.substring(0, i) + s.substring(i + 2, s.length());
                                i--;
                            }
                            break;
                    }
                }
                if (s.equals("")) answer++;
            }
        }
        return answer;
    }
}
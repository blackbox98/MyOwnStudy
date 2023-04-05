class Solution {
    public int solution(String s) {
        int answer = s.length();
        int len = 1;
        while (len <= s.length() / 2) {
            StringBuilder sb = new StringBuilder();
            String target = s.substring(0, len);
            int cnt = 1;
            for (int i = len; i < s.length(); i++) {
                if (i + len <= s.length()) {
                    String tmp = s.substring(i, i + len);
                    if (target.equals(tmp)) {
                        cnt++;
                    } else {
                        if (cnt > 1) sb.append(cnt);
                        sb.append(target);
                        cnt = 1;
                        target = tmp;
                    }
                    i += len - 1;
                } else {
                    if (target.equals("" + s.charAt(i))) {
                        cnt++;
                        continue;
                    }
                    else if (cnt > 0) {
                        if (cnt > 1) sb.append(cnt);
                        sb.append(target);
                        cnt = 0;
                    }
                    sb.append(s.charAt(i));
                }
            }
            if (cnt > 0) {
                if (cnt > 1) sb.append(cnt);
                sb.append(target);
            }
            len++;
            answer = answer > sb.length() ? sb.length() : answer;
        }
        return answer;
    }
}
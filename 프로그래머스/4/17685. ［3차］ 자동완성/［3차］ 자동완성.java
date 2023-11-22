import java.util.Arrays;

class Solution {
    public int solution(String[] words) {
        int answer = 0;
        int N = words.length;
        Arrays.sort(words);
        if (words[1].contains(words[0])) answer += words[0].length();
        else {
            for (int i = 0; i < Math.min(words[0].length(), words[1].length()); i++) {
                answer++;
                if (words[0].charAt(i) != words[1].charAt(i)) break;
            }
        }
        for (int i = N - 1; i > 0; i--) {
            int cnt1 = 0;
            if (words[i].contains(words[i - 1])) cnt1 = words[i - 1].length() + 1;
            else {
                for (int j = 0; j < Math.min(words[i - 1].length(), words[i].length()); j++) {
                    cnt1++;
                    if (words[i].charAt(j) != words[i - 1].charAt(j)) break;
                }
            }
            int cnt2 = 0;
            if (i + 1 < N) {
                for (int j = 0; j < Math.min(words[i].length(), words[i + 1].length()); j++) {
                    cnt2++;
                    if (words[i].charAt(j) != words[i + 1].charAt(j)) break;
                }
            }
            answer += Math.max(cnt1, cnt2);
        }
        return answer;
    }
}
import java.util.Set;
import java.util.HashSet;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[] {0, 0};
        Set<String> set = new HashSet<>();
        int num = 0;
        int turn = 0;
        char lastAlpha = words[0].charAt(0);
        boolean goal = true;
        for (String word : words) {
            if (++num % n == 1) turn++;
            if (lastAlpha != word.charAt(0) || set.contains(word)) {
                goal = false;
                break;
            }
            set.add(word);
            lastAlpha = word.charAt(word.length() - 1);
        }
        if (!goal) answer = new int[] {num % n == 0 ? n : num % n, turn};
        return answer;
    }
}
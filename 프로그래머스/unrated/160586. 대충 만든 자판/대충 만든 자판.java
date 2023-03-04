import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        Map<Character, Integer> alphaMap = new HashMap<>();
        for (String key : keymap) {
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                int min = alphaMap.getOrDefault(c, Integer.MAX_VALUE);
                alphaMap.put(c, min < (i + 1) ? min : (i + 1));
            }
        }
        int idx = 0;
        for (String target : targets) {
            for (int i = 0; i < target.length(); i++) {
                char c = target.charAt(i);
                if (alphaMap.containsKey(c)) {
                    answer[idx] += alphaMap.get(c);
                } else {
                    answer[idx] = -1;
                    break;
                }
            }
            idx++;
        }
        return answer;
    }
}
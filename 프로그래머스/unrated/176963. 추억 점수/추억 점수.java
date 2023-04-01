import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        Map<String, Integer> person = new HashMap<>();
        for (int i = 0; i < name.length; i++) {
            person.put(name[i], yearning[i]);
        }
        for (int i = 0; i < photo.length; i++) {
            for (String member : photo[i]) {
                answer[i] += person.getOrDefault(member, 0);
            }
        }
        return answer;
    }
}
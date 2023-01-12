import java.util.*;
class Solution {
    public String solution(String[] survey, int[] choices) {
        StringBuilder answer = new StringBuilder();
        HashMap<Character, Integer> scores = new HashMap<>();
        scores.put('R', 0);
        scores.put('T', 0);
        scores.put('C', 0);
        scores.put('F', 0);
        scores.put('J', 0);
        scores.put('M', 0);
        scores.put('A', 0);
        scores.put('N', 0);
        for (int i = 0; i < survey.length; i++) {
            if (choices[i] < 4) {
                scores.put(survey[i].charAt(0), scores.get(survey[i].charAt(0)) + (4 - choices[i]));
            } else if (choices[i] > 4) {
                scores.put(survey[i].charAt(1), scores.get(survey[i].charAt(1)) + (choices[i] - 4));
            }
        }
        answer
            .append(scores.get('R') >= scores.get('T') ? "R" : "T")
            .append(scores.get('C') >= scores.get('F') ? "C" : "F")
            .append(scores.get('J') >= scores.get('M') ? "J" : "M")
            .append(scores.get('A') >= scores.get('N') ? "A" : "N");
        return answer.toString();
    }
}
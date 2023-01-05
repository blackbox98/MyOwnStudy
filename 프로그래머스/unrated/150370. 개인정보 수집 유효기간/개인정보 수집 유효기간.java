import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> answerList = new ArrayList<>();
        String[] todayDetail = today.split("\\.");
        int todayTotal = Integer.parseInt(todayDetail[0]) * 12 * 28 +
                Integer.parseInt(todayDetail[1]) * 28 +
                Integer.parseInt(todayDetail[2]);
        HashMap<Character, Integer> termList = new HashMap<Character, Integer>() {{
            for (String term : terms) {
                String[] termDetail = term.split(" ");
                put(termDetail[0].charAt(0), Integer.parseInt(termDetail[1]));
            }
        }};
        for (int i = 0; i < privacies.length; i++) {
            String[] s = privacies[i].split(" ");
            String[] date = s[0].split("\\.");
            int dateTotal = Integer.parseInt(date[0]) * 12 * 28 +
                    Integer.parseInt(date[1]) * 28 +
                    Integer.parseInt(date[2]);
            int term = termList.get(s[1].charAt(0));
            dateTotal += term * 28 - 1;
            if (todayTotal > dateTotal) answerList.add(i + 1);
        }
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}
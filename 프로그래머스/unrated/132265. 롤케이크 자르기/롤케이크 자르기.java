import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int[] topping) {
        Map<Integer, Integer> elder = new HashMap<>();
        Map<Integer, Integer> younger = new HashMap<>();
        int elderCnt = 0;
        int youngerCnt = 0;
        int answer = 0;
        for (int toppingNum : topping) {
            younger.put(toppingNum, younger.getOrDefault(toppingNum, 0) + 1);
            if (younger.get(toppingNum) == 1) youngerCnt++;
        }
        for (int toppingNum : topping) {
            elder.put(toppingNum, elder.getOrDefault(toppingNum, 0) + 1);
            if (elder.get(toppingNum) == 1) elderCnt++;
            younger.put(toppingNum, younger.get(toppingNum) - 1);
            if (younger.get(toppingNum) == 0) youngerCnt--;
            if (elderCnt == youngerCnt) answer++;
        }
        return answer;
    }
}
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : tangerine) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        List<Integer> valueList = new ArrayList<>(map.values());
        valueList.sort(Collections.reverseOrder());
        for (int value : valueList) {
            k -= value;
            answer++;
            if (k <= 0) break;
        }
        return answer;
    }
}
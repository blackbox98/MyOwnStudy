import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : tangerine) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        int[] values = map.values().stream().mapToInt(n -> n).toArray();
        Arrays.sort(values);
        int sum = 0;
        for (int i = values.length - 1; i >= 0; i--) {
            sum += values[i];
            answer++;
            if (sum >= k) break;
        }
        return answer;
    }
}
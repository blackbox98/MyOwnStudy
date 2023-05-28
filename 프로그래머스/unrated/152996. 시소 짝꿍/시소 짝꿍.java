import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        Arrays.sort(weights);
        Map<Double, Integer> map = new HashMap<>();
        for (int weight : weights) {
            double d1 = weight * 1.0;
            double d2 = (d1 * 2) / 3;
            double d3 = (d1 * 2) / 4;
            double d4 = (d1 * 3) / 4;
            if (map.containsKey(d1)) answer += map.get(d1);
            if (map.containsKey(d2)) answer += map.get(d2);
            if (map.containsKey(d3)) answer += map.get(d3);
            if (map.containsKey(d4)) answer += map.get(d4);
            map.put(d1, map.getOrDefault(d1, 0) + 1);
        }
        return answer;
    }
}
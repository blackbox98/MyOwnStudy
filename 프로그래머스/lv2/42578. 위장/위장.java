import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<>();
        for (String[] cloth : clothes) {
            map.put(cloth[1], map.getOrDefault(cloth[1], 0) + 1);
        }
        Iterator<Integer> iter = map.values().iterator();
        while (iter.hasNext()) {
            answer *= iter.next() + 1;
        }
        return answer - 1;
    }
}
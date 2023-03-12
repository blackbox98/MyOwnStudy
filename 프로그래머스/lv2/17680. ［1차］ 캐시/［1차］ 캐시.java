import java.util.LinkedHashSet;
import java.util.Set;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if (cacheSize == 0) return cities.length * 5;
        Set<String> set = new LinkedHashSet<>();
        for (String city : cities) {
            city = city.toLowerCase();
            if (set.isEmpty()) {
                set.add(city);
                answer += 5;
            } else if (set.contains(city)) {
                set.remove(city);
                set.add(city);
                answer += 1;
            } else {
                if (set.size() == cacheSize) {
                    String target = set.iterator().next();
                    set.remove(target);
                }
                set.add(city);
                answer += 5;
            }
        }
        return answer;
    }
}
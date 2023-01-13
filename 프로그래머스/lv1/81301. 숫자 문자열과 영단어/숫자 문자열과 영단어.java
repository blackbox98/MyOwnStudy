import java.util.*;
class Solution {
    public int solution(String s) {
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> map = new HashMap<>();
        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) - '0' < 10) sb.append(s.charAt(i));
            else {
                for (int j = i + 1; j <= s.length(); j++) {
                    if (map.containsKey(s.substring(i, j))) {
                        sb.append(map.get(s.substring(i, j)));
                        i = j - 1;
                        break;
                    }
                }
            }
        }
        return Integer.parseInt(sb.toString());
    }
}
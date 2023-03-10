import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        double answer = 0;
        int num = 0;
        int deno = 0;
        String element = "";
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        for (int i = 1; i < str1.length(); i++) {
            element = str1.substring(i - 1, i + 1);
            if (element.replaceAll("[^a-z]", "").length() != 2) continue;
            map1.put(element, map1.getOrDefault(element, 0) + 1);
            deno++;
        }
        for (int i = 1; i < str2.length(); i++) {
            element = str2.substring(i - 1, i + 1);
            if (element.replaceAll("[^a-z]", "").length() != 2) continue;
            map2.put(element, map2.getOrDefault(element, 0) + 1);
            if (!map1.containsKey(element)) deno++;
        }
        for (String word :
                map1.keySet()) {
            if (map2.containsKey(word)) {
                num += Math.min(map1.get(word), map2.get(word));
                deno += Math.max(map1.get(word), map2.get(word)) - map1.get(word);
            }
        }
        if (map1.isEmpty() && map2.isEmpty()) answer = 1L;
        else if (!map1.isEmpty() && !map2.isEmpty()) answer = (double) num / deno;
        return (int) (answer * 65536);
    }
}
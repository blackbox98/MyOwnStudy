import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    static List<Character> menuList;
    static Map<String, Integer> map;
    static Map<Integer, Integer> most;
    static String[] ordersTmp;
    static Set<Integer> courseSet;
    
    public String[] solution(String[] orders, int[] course) {
        menuList = new ArrayList<>();
        for (String order : orders) {
            for (char c : order.toCharArray()) {
                if (!menuList.contains(c)) menuList.add(c);
            }
        }
        Collections.sort(menuList);
        map = new HashMap<>();
        most = new HashMap<>();
        ordersTmp = orders;
        courseSet = new HashSet<>();
        for (int menuCnt : course) {
            courseSet.add(menuCnt);
        }
        combination(new StringBuilder(), 0);
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            if (value < 2) continue;
            if (most.get(key.length()) == value) list.add(key);
        }
        Collections.sort(list);
        String[] answer = new String[list.size()];
        int idx = 0;
        for (String menus : list) {
            answer[idx++] = menus;
        }
        return answer;
    }
    
    private static void combination(StringBuilder sb, int idx) {
        if (idx == menuList.size()) {
            if (courseSet.contains(sb.length())) {
                L : for (String order : ordersTmp) {
                    int len = sb.length();
                    if (order.length() < len) continue;
                    for (char c : sb.toString().toCharArray()) {
                        if (order.indexOf(c) == -1) continue L;
                    }
                    map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
                    most.put(len, Math.max(most.getOrDefault(len, 0), map.get(sb.toString())));
                }
            }
            return;
        }
        sb.append(menuList.get(idx));
        combination(sb, idx + 1);
        sb.deleteCharAt(sb.length() - 1);
        combination(sb, idx + 1);
    }
}
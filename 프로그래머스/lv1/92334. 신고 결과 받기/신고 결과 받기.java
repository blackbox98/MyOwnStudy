import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, Integer> idMap = new HashMap<>();
        Map<String, HashSet<String>> result = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            idMap.put(id_list[i], i);
            result.put(id_list[i], new HashSet<>());
        }
        for (String s : report) {
            String[] str = s.split(" ");
            result.get(str[1]).add(str[0]);
        }
        for (int i = 0; i < id_list.length; i++) {
            HashSet<String> reported = result.get(id_list[i]);
            if (reported.size() >= k) {
                for (String name : reported) {
                    answer[idMap.get(name)]++;
                }
            }
        }
        return answer;
    }
}
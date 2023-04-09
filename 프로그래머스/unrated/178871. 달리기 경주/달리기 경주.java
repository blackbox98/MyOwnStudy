import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        Map<String, Integer> map1 = new HashMap<>();
        Map<Integer, String> map2 = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            map1.put(players[i], i);
            map2.put(i, players[i]);

        }
        for (String calling : callings) {
            int ranking = map1.get(calling);
            String back = map2.get(ranking - 1);
            map1.put(back, ranking);
            map1.put(calling, ranking - 1);
            map2.put(ranking, back);
            map2.put(ranking - 1, calling);
        }
        Iterator<String> iter = map1.keySet().iterator();
        while (iter.hasNext()) {
            String player = iter.next();
            answer[map1.get(player)] = player;
        }
        return answer;
    }
}
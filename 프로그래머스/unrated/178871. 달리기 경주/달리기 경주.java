import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            map.put(players[i], i);
        }
        for (String calling : callings) {
            int ranking = map.get(calling);
            String front = players[ranking - 1];
            players[ranking] = front;
            players[ranking - 1] = calling;
            map.put(front, ranking);
            map.put(calling, ranking - 1);
        }
        return players;
    }
}
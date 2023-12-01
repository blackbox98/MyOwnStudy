import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<Long, Long> map;
    
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        map = new HashMap<>();
        for (int i = 0; i < answer.length; i++) {
            answer[i] = getRoomNum(room_number[i]);
        }
        return answer;
    }
    
    public long getRoomNum(long roomNum) {
        if (!map.containsKey(roomNum)) {
            map.put(roomNum, roomNum + 1);
            return roomNum;
        }
        map.put(roomNum, getRoomNum(map.get(roomNum)));
        return map.get(roomNum);
    }
}
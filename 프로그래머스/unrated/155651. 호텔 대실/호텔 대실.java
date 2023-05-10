import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(String[][] book_time) {
        int[][] times = new int[book_time.length][2];
        for (int i = 0; i < book_time.length; i++) {
            String[] start_time = book_time[i][0].split(":");
            String[] end_time = book_time[i][1].split(":");
            int start = Integer.parseInt(start_time[0]) * 60 + Integer.parseInt(start_time[1]);
            int end = Integer.parseInt(end_time[0]) * 60 + Integer.parseInt(end_time[1]);
            times[i][0] = start;
            times[i][1] = end;
        }
        Arrays.sort(times, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0];
        });
        Queue<int[]> room = new LinkedList<>();
        for (int[] time : times) {
            int start = time[0];
            int end = time[1];
            if (room.isEmpty()) room.offer(new int[]{start, end});
            else {
                for (int i = 0; i < room.size(); i++) {
                    int[] current = room.poll();
                    if (current[1] + 10 <= start) break;
                    else room.offer(current);
                }
                room.offer(new int[]{start, end});
            }
        }
        return room.size();
    }
}
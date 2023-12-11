import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        if (works.length == 1) return works[0] - n <= 0 ? 0 : (long) (works[0] - n) * (works[0] - n);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) pq.offer(work);
        while (!pq.isEmpty() && n-- > 0) {
            if (pq.peek() == 0) return 0;
            pq.offer(pq.poll() - 1);
        }
        long answer = 0;
        while (!pq.isEmpty()) answer += (long) pq.peek() * pq.poll();
        return answer;
    }
}
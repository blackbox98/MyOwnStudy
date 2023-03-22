import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        Queue<Integer> queue = new PriorityQueue<>();
        for (int spicy : scoville) {
            queue.offer(spicy);
        }
        while (!queue.isEmpty() && queue.size() > 1) {
            if (queue.peek() >= K) break;
            int first = queue.poll();
            int second = queue.poll();
            queue.offer(first + (second * 2));
            answer++;
        }
        if (!queue.isEmpty() && queue.peek() < K) answer = -1;
        return answer;
    }
}
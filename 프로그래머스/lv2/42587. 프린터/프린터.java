import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> cntPri = new PriorityQueue<>(Collections.reverseOrder());
        for (int priority : priorities) {
            queue.offer(priority);
            cntPri.offer(priority);
        }
        while (!queue.isEmpty()) {
            int top = cntPri.peek();
            int target = queue.poll();
            if (target == top) {
                answer++;
                cntPri.poll();
                if (location == 0) {
                    break;
                }
            } else queue.offer(target);
            location = location - 1 < 0 ? queue.size() - 1 : location - 1;
        }
        return answer;
    }
}
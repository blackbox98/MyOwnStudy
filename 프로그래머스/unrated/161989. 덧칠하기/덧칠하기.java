import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        if (section.length == 1) {
            answer = section[0] / m;
            if (section[0] % m > 0) answer++;
        } else {
            Queue<Integer> queue = new LinkedList<>();
            for (int num : section) {
                queue.offer(num);
            }
            while (!queue.isEmpty()) {
                int start = queue.poll();
                int end = start;
                while (!queue.isEmpty()) {
                    if (queue.peek() - start + 1 <= m) {
                        end = queue.poll();
                    } else break;
                }
                int area = end - start + 1;
                answer += area / m;
                if (area % m > 0) answer++;
            }
        }
        return answer;
    }
}
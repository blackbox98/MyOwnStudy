import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        Queue<Integer> wait = new PriorityQueue<>();
        int lastComplete = -1;
        while (true) {
            int completeIdx = -1;
            boolean breakCheck = true;
            for (int i = 0; i < progresses.length; i++) {
                if (progresses[i] < 100) {
                    breakCheck = false;
                    progresses[i] += speeds[i];
                    if (progresses[i] >= 100) {
                        if (lastComplete + 1 == i) completeIdx = i;
                        else wait.offer(i);
                    }
                }
            }
            if (completeIdx != -1) {
                lastComplete++;
                int cnt = 0;
                while (!wait.isEmpty() && wait.peek() == lastComplete + 1) {
                    cnt++;
                    lastComplete++;
                    wait.poll();
                }
                answer.add(cnt + 1);
            }
            if (breakCheck) break;
        }
        return answer.stream().mapToInt(n -> n).toArray();
    }
}
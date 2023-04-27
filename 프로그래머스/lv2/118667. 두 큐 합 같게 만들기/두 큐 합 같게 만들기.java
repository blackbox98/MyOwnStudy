import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1 = 0;
        long sum2 = 0;
        for (int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            sum2 += queue2[i];
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
        }
        if ((sum1 + sum2) % 2 != 0) return -1;

        int answer = 0;
        int size = queue1.length * 2;
        while (sum1 != sum2) {
            if (sum1 > sum2) {
                int num = q1.poll();
                sum1 -= num;
                sum2 += num;
                q2.offer(num);
            }
            else {
                int num = q2.poll();
                sum1 += num;
                sum2 -= num;
                q1.offer(num);
            }
            if (answer > size * 2) {
                answer = -1;
                break;
            }
            answer++;
        }
        return answer;
    }
}
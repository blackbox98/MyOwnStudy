import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> ascPQ = new PriorityQueue<>();
        PriorityQueue<Integer> descPQ = new PriorityQueue<>(Collections.reverseOrder());
        for (String operation : operations) {
            String[] tmp = operation.split(" ");
            if (tmp[0].equals("I")) {
                ascPQ.offer(Integer.parseInt(tmp[1]));
                descPQ.offer(Integer.parseInt(tmp[1]));
            }
            else if (tmp[1].equals("-1") && !ascPQ.isEmpty()) descPQ.remove(ascPQ.poll());
            else if (tmp[1].equals("1") && !descPQ.isEmpty()) ascPQ.remove(descPQ.poll());
        }
        return new int[]{descPQ.isEmpty() ? 0 : descPQ.poll(), ascPQ.isEmpty() ? 0 : ascPQ.poll()};
    }
}
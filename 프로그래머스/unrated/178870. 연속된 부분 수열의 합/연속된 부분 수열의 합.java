import java.util.PriorityQueue;

class Solution {
    public int[] solution(int[] sequence, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            int len1 = o1[1] - o1[0];
            int len2 = o2[1] - o2[0];
            if (len1 == len2) return o1[0] - o2[0];
            else return len1 - len2;
        });
        int left = 0;
        int right = 0;
        int sum = sequence[0];
        int len = sequence.length;
        while (left < len && right < len && sequence[right] <= k) {
            if (sum == k) queue.offer(new int[]{left, right});
            if (sum <= k && ++right < len) sum += sequence[right];
            else sum -= sequence[left++];
        }
        return queue.poll();
    }
}
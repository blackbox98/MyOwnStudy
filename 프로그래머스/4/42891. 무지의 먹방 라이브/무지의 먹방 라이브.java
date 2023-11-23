import java.util.*;

class Solution {
    private static class Food implements Comparable<Food> {
        int idx;
        int time;

        private Food(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }

        @Override
        public int compareTo(Food o) {
            return this.time - o.time;
        }
    }
    
    public int solution(int[] food_times, long k) {
        int size = food_times.length;
        long total = 0;
        PriorityQueue<Food> pq = new PriorityQueue<>();
        for (int i = 0; i < size; i++) {
            total += food_times[i];
            pq.offer(new Food(i + 1, food_times[i]));
        }
        if (total <= k) return -1;

        total = 0;
        long eat = 0;
        while (!pq.isEmpty()) {
            if (total + ((pq.peek().time - eat) * size) > k) break;
            total += (pq.peek().time - eat) * size--;
            eat = pq.poll().time;
        }

        Food[] arr = new Food[size];
        int idx = 0;
        while (!pq.isEmpty()) arr[idx++] = pq.poll();
        Arrays.sort(arr, Comparator.comparingInt(o -> o.idx));
        return arr[(int) ((k - total) % size)].idx;
    }
}
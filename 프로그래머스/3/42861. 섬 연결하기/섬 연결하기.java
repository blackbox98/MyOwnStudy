import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        List<int[]>[] list = new List[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int[] cost : costs) {
            list[cost[0]].add(new int[]{cost[1], cost[2]});
            list[cost[1]].add(new int[]{cost[0], cost[2]});
        }
        return prim(list, n, 0);
    }
    
    public int prim(List<int[]>[] list, int n, int start) {
        int totalCost = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{start, 0});
        boolean[] v = new boolean[n];
        while (!pq.isEmpty()) {
            int[] curNode = pq.poll();
            if (v[curNode[0]]) continue;
            v[curNode[0]] = true;
            totalCost += curNode[1];
            for (int[] next : list[curNode[0]]) {
                if (v[next[0]]) continue;
                pq.offer(new int[]{next[0], next[1]});
            }
        }
        return totalCost;
    }
}
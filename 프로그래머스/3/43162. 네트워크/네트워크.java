import java.util.*;

class Solution {
    Queue<Integer> queue;
    boolean[] v;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        queue = new LinkedList<>();
        v = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (v[i]) continue;
            bfs(computers, n, i);
            answer++;
        }
        return answer;
    }
    
    public void bfs(int[][] computers, int n, int start) {
        queue.offer(start);
        v[start] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 0; i < n; i++) {
                if (computers[cur][i] == 0 || v[i]) continue;
                queue.offer(i);
                v[i] = true;
            }
        }
    }
}
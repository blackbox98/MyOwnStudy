import java.util.*;

class Solution {
    List<Integer>[] graph;
    Map<Integer, Integer> after, before;
    
    public boolean solution(int n, int[][] path, int[][] order) {
        graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] p : path) {
            graph[p[0]].add(p[1]);
            graph[p[1]].add(p[0]);
        }
        after = new HashMap<>();
        before = new HashMap<>();
        for (int[] o : order) {
            if (o[1] == 0) return false;
            after.put(o[0], o[1]);
            before.put(o[1], o[0]);
        }
        return bfs(n);
    }
    
    public boolean bfs(int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        boolean[] v_final = new boolean[n];
        boolean[] v = new boolean[n];
        v_final[0] = true;
        int cnt = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            cnt++;
            for (int next : graph[cur]) {
                if (v_final[next]) continue;
                else if (before.containsKey(next) && !v_final[before.get(next)]) {
                    v[next] = true;
                    continue;
                }
                queue.offer(next);
                v_final[next] = true;
            }
            if (!after.containsKey(cur)) continue;
            int next = after.get(cur);
            if (v[next]) {
                queue.offer(next);
                v_final[next] = true;
            }
        }
        return cnt == n;
    }
}
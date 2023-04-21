import java.util.*;

class Solution {
    static boolean[] v;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        for (int ignore = 0; ignore < wires.length; ignore++) {
            ArrayList<Integer>[] arr = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                arr[i] = new ArrayList<>();
            }
            for (int i = 0; i < wires.length; i++) {
                if (i == ignore) continue;
                int s = wires[i][0];
                int e = wires[i][1];
                arr[s].add(e);
                arr[e].add(s);
            }
            v = new boolean[n + 1];
            int cnt = 0;
            for (int start = 1; start <= n; start++) {
                if (v[start]) continue;
                cnt = Math.abs(cnt - check(arr, start));
            }
            answer = Math.min(answer, cnt);
        }
        return answer;
    }
    
    private static int check(ArrayList<Integer>[] arr, int startNum) {
        int cnt = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startNum);
        while (!queue.isEmpty()) {
            int start = queue.poll();
            v[start] = true;
            cnt++;
            for (int i = 0; i < arr[start].size(); i++) {
                int end = arr[start].get(i);
                if (v[end]) continue;
                queue.offer(end);
            }
        }
        return cnt;
    }
}
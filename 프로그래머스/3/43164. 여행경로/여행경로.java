import java.util.PriorityQueue;

class Solution {
    PriorityQueue<String> pq;
    
    public String[] solution(String[][] tickets) {
        pq = new PriorityQueue<>();
        dfs(tickets, new boolean[tickets.length], "ICN", "ICN", 0);
        return pq.poll().split(" ");
    }
    
    public void dfs(String[][] tickets, boolean[] v, String path, String cur, int depth) {
        if (depth == tickets.length) {
            pq.offer(path);
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            if (!cur.equals(tickets[i][0]) || v[i]) continue;
            v[i] = true;
            dfs(tickets, v, path + " " + tickets[i][1], tickets[i][1], depth + 1);
            v[i] = false;
        }
    }
}
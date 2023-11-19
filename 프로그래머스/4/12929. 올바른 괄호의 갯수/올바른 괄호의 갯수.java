class Solution {
    int answer;
    
    public int solution(int n) {
        answer = 0;
        dfs(n, 1, 1);
        return answer;
    }
    
    public void dfs(int n, int idx, int sum) {
        if (idx == n * 2) {
            if (sum == 0) answer++;
            return;
        }
        if (sum > n || sum < 0) return;
        dfs(n, idx + 1, sum + 1);
        dfs(n, idx + 1, sum - 1);
    }
}
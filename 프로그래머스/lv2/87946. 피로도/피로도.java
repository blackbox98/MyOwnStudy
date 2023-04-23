class Solution {
    static int answer = 0;
    static int size;
    static boolean[] v;
    
    public int solution(int k, int[][] dungeons) {
        size = dungeons.length;
        v = new boolean[size];
        dfs(dungeons, k, 0);
        return answer;
    }
    
    private static void dfs(int[][] dungeons, int k, int cnt) {
        answer = Math.max(answer, cnt);
        for (int i = 0; i < size; i++) {
            int minReqFat = dungeons[i][0];
            int consumeFat = dungeons[i][1];
            if (!v[i] && k >= minReqFat) {
                v[i] = true;
                dfs(dungeons, k - consumeFat, cnt + 1);
                v[i] = false;
            }
        }
    }
}
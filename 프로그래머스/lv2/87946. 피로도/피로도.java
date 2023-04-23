class Solution {
    static int answer = 0;
    static int size;
    static boolean[] v;
    
    public int solution(int k, int[][] dungeons) {
        size = dungeons.length;
        v = new boolean[size];
        dfs(dungeons, k, 0, 0);
        return answer;
    }
    
    private static void dfs(int[][] dungeons, int k, int idx, int cnt) {
        if (idx == size) {
            answer = Math.max(answer, cnt);
            return;
        }
        for (int i = 0; i < size; i++) {
            int minReqFat = dungeons[i][0];
            int consumeFat = dungeons[i][1];
            if (!v[i]) {
                if (k >= minReqFat) {
                    v[i] = true;
                    dfs(dungeons, k - consumeFat, idx + 1, cnt + 1);
                    v[i] = false;
                } else dfs(dungeons, k, idx + 1, cnt);
            }
        }
    }
}
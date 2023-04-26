class Solution {
    static int[] answer = new int[]{-1};
    static int answerMax;
    
    public int[] solution(int n, int[] info) {
        answerMax = 0;
        dfs(info, new int[info.length], n);
        return answer;
    }
    
    private static void dfs(int[] info, int[] arr, int n) {
        if (n == 0) {
            int APEACH = 0;
            int RYAN = 0;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > info[i]) RYAN += 10 - i;
                else if (info[i] > 0) APEACH += 10 - i;
            }
            if (APEACH < RYAN && answerMax <= RYAN - APEACH) {
                answerMax = RYAN - APEACH;
                answer = arr.clone();
            }
            return;
        }

        for (int i = 0; i < arr.length && arr[i] <= info[i]; i++) {
            arr[i]++;
            dfs(info, arr, n - 1);
            arr[i]--;
        }
    }
}
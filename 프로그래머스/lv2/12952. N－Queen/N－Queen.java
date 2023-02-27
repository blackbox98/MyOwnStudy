class Solution {
    static int answer, N;
    static int[] queen;
    
    public int solution(int n) {
        answer = 0;
        N = n;
        queen = new int[n];
        find(0);
        return answer;
    }
    
    private static void find(int depth) {
        if (depth == N) {
            answer++;
            return;
        }
        for (int i = 0; i < N; i++) {
            queen[depth] = i;
            if (check(depth)) find(depth + 1);
        }
    }

    private static boolean check(int depth) {
        for (int i = 0; i < depth; i++) {
            if (queen[depth] == queen[i] || Math.abs(queen[depth] - queen[i]) == Math.abs(depth - i)) return false;
        }
        return true;
    }
}
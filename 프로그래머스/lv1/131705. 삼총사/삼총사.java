class Solution {
    static int answer = 0;
    public int solution(int[] number) {
        combination(number, new int[3], new boolean[number.length], 0, 0);
        return answer;
    }
    
    private static void combination(int[] number, int[] sel, boolean[] v, int idx, int k) {
        if (k == sel.length) {
            if (sel[0] + sel[1] + sel[2] == 0) answer++;
            return;
        }

        for (int i = idx; i < v.length; i++) {
            if (!v[i]) {
                v[i] = true;
                sel[k] = number[i];
                combination(number, sel, v, i + 1, k + 1);
                v[i] = false;
            }
        }
    }
}
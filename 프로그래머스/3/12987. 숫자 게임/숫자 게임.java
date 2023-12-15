import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int answer = 0;
        int idx = 0;
        int len = A.length;
        for (int i = 0; idx < len && i < len; i++) {
            while (idx < len) {
                if (B[idx++] > A[i]) {
                    answer++;
                    break;
                }
            }
        }
        return answer;
    }
}
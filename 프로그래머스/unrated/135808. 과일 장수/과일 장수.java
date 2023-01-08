import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        int cheap = Integer.MAX_VALUE;
        int cnt = 0;
        int idx = 0;
        Integer[] tmp = Arrays.stream(score).boxed().toArray(Integer[]::new);
        Arrays.sort(tmp, Comparator.reverseOrder());
        while (idx < score.length) {
            if (cnt < m) {
                cnt++;
                cheap = Math.min(cheap, tmp[idx]);
                if (cnt == m) {
                    answer += cheap * m;
                    cnt = 0;
                    cheap = Integer.MAX_VALUE;
                }
            }
            idx++;
        }
        return answer;
    }
}
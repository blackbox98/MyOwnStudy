import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt(o -> o[1]));
        int answer = 0;
        int curPoint = -30001;
        for (int[] route : routes) {
            if (curPoint < route[0]) {
                curPoint = route[1];
                answer++;
            }
        }
        return answer;
    }
}
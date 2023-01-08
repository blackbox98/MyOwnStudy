import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int[] subArray;
        for(int i = 0; i < commands.length; i++) {
            int start = commands[i][0] - 1;
            int end = commands[i][1] - 1;
            subArray = new int[end - start + 1];
            for(int j = start; j <= end; j++) {
                subArray[j - start] = array[j];
            }
            Arrays.sort(subArray);
            answer[i] = subArray[commands[i][2] - 1];
        }
        return answer;
    }
}
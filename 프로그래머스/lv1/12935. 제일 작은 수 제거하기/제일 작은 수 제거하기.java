import java.util.*;
class Solution {
    public int[] solution(int[] arr) {
        List<Integer> answer = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int n : arr) {
            min = Math.min(min, n);
        }
        for (int n : arr) {
            if (n != min) answer.add(n);
        }
        if (answer.isEmpty()) answer.add(-1);
        return answer.stream().mapToInt(n -> n).toArray();
    }
}